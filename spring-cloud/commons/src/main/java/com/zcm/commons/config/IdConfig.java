package com.zcm.commons.config;
import com.zcm.commons.tool.*;
import lombok.extern.slf4j.*;
import org.springframework.context.annotation.*;

import javax.management.*;
import java.lang.management.*;
import java.net.*;
import java.util.*;

/**
 * @ClassName: IdConfig
 * @Author: FJW
 * @Date: 2021/4/28 15:46
 * @Version: 1.0.0
 * @Description: ID生成器配置
 */
@Slf4j
@Configuration
public class IdConfig {
    private static int port = 0;
    long mac = 0;

    long portSnow;
    long macSnow;

    @Bean("snowFlakeGenerator")
    @Scope("prototype")
    public SnowFlakeGenerator snowFlakeGenerator() {
        if (port == 0 && mac == 0) {
            this.getPortAndMac();
        }
        return SnowFlakeGenerator.Factory.create(portSnow, macSnow);
    }


    @Bean("uUidGenerator")
    public UUidGenerator uUidGenerator() {
        if (port == 0 && mac == 0) {
            this.getPortAndMac();
        }
        return new UUidGenerator(port % 99, (int) mac % 99);
    }

    /**
     * 获取服务端口与Mac
     */
    private void getPortAndMac() {

        //获取监听端口
        String listenPort = "8080";
        try {
            ObjectName objectName = new ObjectName("*:type=Connector,*");
            MBeanServer beanServer = ManagementFactory.getPlatformMBeanServer();
            Set<ObjectName> objectNames = beanServer.queryNames(objectName,
                    Query.match(Query.attr("protocol"), Query.value("HTTP/1.1")));
            int size = objectNames.size();
            if (size > 0) {
                ObjectName next = objectNames.iterator().next();
                if (next != null) {
                    listenPort = next.getKeyProperty("port");
                }
            }

        } catch (MalformedObjectNameException e) {
            log.error("获取端口异常", e);
        }
        port = Integer.parseInt(listenPort);
        if (port < 0) {
            port = -port;
        }
        //获取Mac地址
        try {
            InetAddress localHost = InetAddress.getLocalHost();
            NetworkInterface network = NetworkInterface.getByInetAddress(localHost);
            byte[] macArr = network.getHardwareAddress();
            StringBuilder sb = new StringBuilder(12);
            int length = macArr.length - 1;
            for (int i = 0; i < length; i++) {
                sb.append(String.format("%02X", macArr[i]));
            }
            sb.append(String.format("%02X", macArr[length]));
            String macS = sb.toString();
            mac = Long.parseLong(macS, 16);
        } catch (Exception e) {
            log.error("获取MAC异常", e);
        }
        if (mac < 0) {
            mac = -mac;
        }
        portSnow = port % SnowFlakeGenerator.getMaxWorkerId();
        macSnow = mac % SnowFlakeGenerator.getMaxDataCenterId();
    }

    public static int getPort() {
        return port;
    }
}
