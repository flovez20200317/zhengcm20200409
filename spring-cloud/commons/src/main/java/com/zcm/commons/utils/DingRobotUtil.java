package com.zcm.commons.utils;
import cn.hutool.http.*;
import com.alibaba.fastjson.*;
import lombok.extern.slf4j.*;

import java.util.*;

/**
 * @ClassName: DingRobotUtil
 * @Author: zcm
 * @Date: 2021/4/28 15:27
 * @Version: 1.0.0
 * @Description: 钉钉机器人消息推送
 */
@Slf4j
public class DingRobotUtil {
    // 钉钉的webhook
    private static final String DINGDING_TOKEN = "https://oapi.dingtalk.com/robot/send?access_token=3cb52c0a6e90dda89a9d2dd5a1aaac02448eb2412f72d8ea58c1b35b006cedc5";
    //钉钉推送运营信息链接
    private static final String DINGDING_PUSH = "https://oapi.dingtalk.com/robot/send";

    /**
     * 相册活跃度推送token
     * v2.4 更名 - 每日老客户数据群
     */
    public static final String TOKEN_ACTIVE = "afc62ad2d26881421ba113cc97898764dff3a15dc82b3db43e288ec008a39f76";
    /**
     * 运营https://oapi.dingtalk.com/robot/send?access_token=a26c540b61a7bba040e9b912d91dff7e655e7b40f9ca5a2d7513ef52e61f1c46
     * v2.4 更名 - 每日新客数据群
     */
    public static final String TOKEN_OPERATION = "d9fa9048cd5681aa8849dc51945615e31edcd41f4a360843f79413b01a1beeb8";
    //  测试群
    public static final String TOKEN_OPERATION_TEST = "a26c540b61a7bba040e9b912d91dff7e655e7b40f9ca5a2d7513ef52e61f1c46";

    //觅享相册公众信息回复
    public static final String TOKEN_OFFICE_MESSAGE = "235645e26a2f4211d8616f85ec9fad473259a583c9776629e42c731b5289a95d";

    //相册错误日志推送
    public static final String TOKEN_ERROR = "ea357d76af394405da4541d5715d6e8e0d0bb5e2be494d2536e00717582d3e44";

    /**
     * 测试推送token
     */
    public static final String TOKEN_TEST = "005f2651aca6990e365136f29a23f80babd76af93e00d137c3747e5668e2eb47";


    /**
     * 微商相册搬家
     */
    public static final String SZWEGO_FAN = "99de09209cb41d8c4900f0415f3a5098844f2c5b1aa0dfc8f81cc0462ff88073";


    /**
     * msgtype:消息类型，此时固定为：text
     * content:消息内容
     * atMobiles:被@人的手机号(在content里添加@人的手机号)(array)
     * isAtAll: @所有人时：true，否则为：false (bool)
     *
     * @author: jinyang
     * @time: 2019/12/9
     */
    public static String DingTalk(String content) {
        // 请求的JSON数据，用map在工具类里转成json格式
        Map<String, Object> parameter = new HashMap();
        Map<String, Object> text = new HashMap();

        parameter.put("msgtype", "text");
        text.put("content", content);
        parameter.put("text", text);

        // 发送post请求
        String json = JSONObject.toJSONString(parameter);
        String response = HttpUtil.doJsonPost(DINGDING_TOKEN, json);
        return response;
    }

    /**
     * 运营信息推送接口
     *
     * @author: jinyang
     * @time: 2019/12/9
     */
    public static String dingTalkOperation(String content) {
        StringBuilder builder = new StringBuilder(128);
        String url = builder.append(DINGDING_PUSH)
                .append("?access_token=").append(TOKEN_OPERATION).toString();

        String response = HttpUtil.doJsonPost(url, content);
        return response;
    }

    public static String dingTalkPublic(String content, String token) {
//        String token = "d9fa9048cd5681aa8849dc51945615e31edcd41f4a360843f79413b01a1beeb8";
        StringBuilder builder = new StringBuilder(128);
        String url = builder.append(DINGDING_PUSH)
                .append("?access_token=").append(token).toString();

        String response = HttpUtil.doJsonPost(url, content);
        log.debug("推送{}", response);
        return response;
    }

    public static Builder create() {
        return new Builder();
    }

    public static MarkdownBuilder createMarkdown() {
        return new MarkdownBuilder();
    }

    /**
     * 信息构造器，仿造JWT构造器
     *
     * @Author sfh
     * @Date 2020/2/15 15:18
     */
    public static class Builder {

        JSONObject parameter = new JSONObject();
        JSONObject textMap = new JSONObject();
        List<String> mobiles = new ArrayList<>();


        public Builder withText(Object text) {
            textMap.put("text", text);
            return this;
        }

        public Builder withContent(Object text) {
            textMap.put("content", text);
            return this;
        }

        public Builder withTitle(String title) {
            textMap.put("title", title);
            return this;
        }

        public Builder withMsgtype(String msgtype) {
            parameter.put("msgtype", msgtype);
            return this;
        }

        public Builder withAtMobiles(String mobile) {
            mobiles.add(mobile);
            return this;
        }

        @Override
        public String toString() {
            String msgtype = (String) parameter.get("msgtype");
            parameter.put(msgtype, textMap);
            if (!mobiles.isEmpty()) {
                Map at = new HashMap(1);
                at.put("atMobiles", mobiles);
                parameter.put("at", at);
            }
            return parameter.toJSONString();
        }

    }


    /**
     * Markdown 语法创建器
     *
     * @version 2.3
     * @Author sfh
     * @Date 2020/10/22 10:15
     */
    public static class MarkdownBuilder {
        /**
         * 加粗
         */
        public static final int FONT_BOLD = 1;
        /**
         * 倾斜
         */
        public static final int FONT_TILT = 2;
        /**
         * 斜体加粗
         */
        public static final int FONT_ITALIC_BOLD = 3;
        /**
         * 删除线
         */
        public static final int FONT_DELETE = 4;


        StringBuilder builder = new StringBuilder(1024);

        public MarkdownBuilder append(String s) {
            builder.append(s);
            return this;
        }

        public MarkdownBuilder append(int i) {
            builder.append(i);
            return this;
        }

        public MarkdownBuilder append(StringBuilder stringBuilder) {
            builder.append(stringBuilder);
            return this;
        }

        /**
         * 标题
         *
         * @param title 标题
         * @param level 等级
         * @return
         */
        public MarkdownBuilder title(String title, int level) {
            for (int i = 0; i < level; i++) {
                builder.append("#");
            }
            builder.append(" ").append(title);
            return this;
        }

        public MarkdownBuilder title(String title) {
            builder.append("##### ").append(title);
            return this;
        }

        public MarkdownBuilder titleEnd() {
            builder.append("\n");
            return this;
        }

        /**
         * 字体
         *
         * @param title
         * @param type
         * @return
         */
        public MarkdownBuilder font(String title, int type) {

            switch (type) {
                case FONT_BOLD:
                    builder.append("**").append(title);
                    break;
                case FONT_DELETE:
                    builder.append("*").append(title);
                    break;
                default:
                    builder.append(title);
                    break;
            }
            return this;
        }

        public MarkdownBuilder fontEnd(int type) {

            switch (type) {
                case FONT_BOLD:
                    builder.append("**\n");
                    break;
                case FONT_DELETE:
                    builder.append("*`\n");
                    break;
                case FONT_ITALIC_BOLD:
                    builder.append("***\n");
                    break;
                case FONT_TILT:
                    builder.append("~~\n");
                    break;
                default:
                    builder.append("\n");
                    break;
            }
            return this;
        }


        /**
         * 引用
         *
         * @param info  信息
         * @param level 等级
         * @return
         */
        public MarkdownBuilder quote(String info, int level) {
            for (int i = 0; i < level; i++) {
                builder.append(">");
            }
            builder.append(info);
            return this;
        }

        public MarkdownBuilder quoteEnd() {
            builder.append("\n");
            return this;
        }

        /**
         * 分割
         *
         * @return
         */
        public MarkdownBuilder slit() {
            builder.append("\n ------------------").append("\n");
            return this;
        }

        /**
         * 图片
         *
         * @param name   名称
         * @param url    地址
         * @param prompt 提示
         * @return
         */
        public MarkdownBuilder image(String name, String url, String prompt) {
            builder.append(" ![").append(name).append("](").append(url).append(" ''").append(prompt).append("'') \n");
            return this;
        }

        /**
         * 超链接
         *
         * @param name 连接名
         * @param url  链接地址
         * @return
         */
        public MarkdownBuilder hyperlinks(String name, String url) {
            builder.append("[").append(name).append("](").append(url).append("\n");
            return this;
        }

        public MarkdownBuilder wrap() {
            builder.append("\n");
            return this;
        }

        @Override
        public String toString() {
            return builder.toString();
        }


    }
}
