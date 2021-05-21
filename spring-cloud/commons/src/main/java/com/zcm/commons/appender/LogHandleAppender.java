package com.zcm.commons.appender;

import ch.qos.logback.classic.*;
import ch.qos.logback.classic.spi.*;
import ch.qos.logback.core.rolling.*;
import com.zcm.commons.utils.*;
import lombok.*;
import lombok.extern.slf4j.*;
import org.springframework.beans.factory.config.*;
import org.springframework.core.io.*;

import javax.servlet.http.*;
import java.util.*;

/**
 * @ClassName: LogHandleAppender
 * @Author: zcm
 * @Date: 2021/4/28 15:24
 * @Version: 1.0.0
 * @Description: 日志处理
 */
@Slf4j
public class LogHandleAppender<E> extends RollingFileAppender<E> {
    /**
     * 工程名
     *
     * @Author sfh
     * @Date 2020/6/18 16:24
     * @version:1.0
     */
    String projectName;
    /**
     * 上传地址
     *
     * @Author sfh
     * @Date 2020/6/18 16:39
     * @version:1.0
     */
    String sendAddress;

    String property;

    boolean isProd = false;


    public LogHandleAppender() {
        Object commonYml = this.getCommonYml("spring.profiles.active");
        if (commonYml != null) {
            property = commonYml.toString();
            if ("test".equals(property) || "prod".equals(property)) {
                isProd = true;
            }
        }

    }

    @Override
    protected void append(E eventObject) {
        super.append(eventObject);
        if (isProd) {
            if (eventObject != null) {
                try {
                    this.sendTask(eventObject);
                } catch (Exception e) {
//                    log.error("append log", e);
                }
            }
        }
    }

    /**
     * 发送日志信息任务
     *
     * @param eventObject
     * @return
     * @Author sfh
     * @Date 2020/6/18 10:02
     * @version:
     */
    private void sendTask(E eventObject) {
        ILoggingEvent event = ((ILoggingEvent) eventObject);
        CommonLog commonLog = new CommonLog();
        commonLog.setProjectName(this.projectName);

        HttpServletRequest request = RequestHolderUtil.getRequest();
        if (request != null) {
            String requestURI = request.getRequestURI();
            commonLog.setRequestUrl(requestURI);
        }
        String formattedMessage = event.getFormattedMessage();
        commonLog.setFunc(event.getLoggerName());
        commonLog.setMessage(formattedMessage);
        String ipAndPort = GetIpAndPortUtil.getIpAndPort();

        commonLog.setIp(ipAndPort);


        DingRobotUtil.MarkdownBuilder markdownBuilder = DingRobotUtil.createMarkdown()
                .title(ipAndPort).titleEnd()
                .title(property).append(formattedMessage).titleEnd().slit();


        IThrowableProxy throwableProxy = event.getThrowableProxy();
        if (throwableProxy != null) {
//            StringBuilder message = new StringBuilder(1024);
//            message.append(throwableProxy.getMessage()).append(" ");
//                        .append("【")
//                        .append(event.getThrowableProxy().getClassName())
//                        .append("】");

            markdownBuilder.quote("【", 1).append(event.getThrowableProxy().getClassName()).append("】").quoteEnd();
            int len = 10;
            int i = 0;
            StackTraceElementProxy[] stackTraceElementProxyArray = throwableProxy.getStackTraceElementProxyArray();
            for (StackTraceElementProxy element : stackTraceElementProxyArray) {
                i++;
                if (i >= len) {
                    break;
                }
                String elementStr = element.toString();
                if (elementStr != null) {
//                    message.append(elementStr);
//                        message.append(CoreConstants.TAB);
//                    message.append(CoreConstants.LINE_SEPARATOR);

                    markdownBuilder.quote(elementStr, 1).quoteEnd();
                }
            }
//            commonLog.setDetailTrace(message.toString());
        }
//        else {
//            markdownBuilder.quote(event.getMessage(), 1).quoteEnd();
////            commonLog.setDetailTrace(event.getMessage());
//        }
//        commonLog.setLogTime(DateUtil.parseLongToStr(event.getTimeStamp(), DateUtil.DATE_TIME_FORMAT_YYYY_MM_DD_HH_MI_SS));
        Level level = event.getLevel();

        switch (level.levelStr) {
            case "INFO":
                commonLog.setLevel(2);
                break;
            default:
            case "ERROR":
                commonLog.setLevel(4);
                break;
            case "WARN":
                commonLog.setLevel(3);
                break;
            case "DEBUG":
                commonLog.setLevel(1);
                break;
        }
//        String jsonParams = JSON.toJSONString(commonLog);
//        String json = HttpUtil.doJsonPost(sendAddress, jsonParams);


        DingRobotUtil.Builder builder = DingRobotUtil.create()
                .withText(markdownBuilder.toString())
                .withMsgtype("markdown")
                .withTitle("ERROR");
        String text = builder.toString();
        DingRobotUtil.dingTalkPublic(text, DingRobotUtil.TOKEN_TEST);
//        DingRobotUtil.dingTalkPublic(text, DingRobotUtil.TOKEN_ERROR);
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public void setSendAddress(String sendAddress) {
        this.sendAddress = sendAddress;
    }


    private static String PROPERTY_NAME = "application.yml";

    /**
     * 获取yml配置
     *
     * @param key
     * @return
     */
    public Object getCommonYml(Object key) {
        Resource resource = new ClassPathResource(PROPERTY_NAME);
        Properties properties = null;
        try {
            YamlPropertiesFactoryBean yamlFactory = new YamlPropertiesFactoryBean();
            yamlFactory.setResources(resource);
            properties = yamlFactory.getObject();
        } catch (Exception e) {
            log.error("yml", e);
            return null;
        }
        return properties.get(key);
    }

}

@Data
class CommonLog {

    /**
     * 日志标识
     */
    private String logId;

    /**
     * 项目名称
     */
    private String projectName;

    /**
     * 日志等级
     */
    private Integer level;

    /**
     * 错误码
     */
    private String errorCode;

    /**
     * 日志标题信息(255字节)
     */
    private String message;

    /**
     * 日志细节跟踪
     */
    private String detailTrace;

    /**
     * 客户端Ip(访问Ip)
     */
    private String ip;

    /**
     * 用户Id
     */
    private String userId;

    /**
     * 浏览器信息
     */
    private String browser;

    /**
     * 服务器名称
     */
    private String machineName;

    /**
     * 备注
     */
    private String tag;

    /**
     * 日志记录时间format: date-time
     */
    private String logTime;

    /**
     * 请求地址
     */
    private String requestUrl;

    /**
     * 功能名称
     */
    private String func;

    /**
     * 请求参数
     */
    private String params;
}
