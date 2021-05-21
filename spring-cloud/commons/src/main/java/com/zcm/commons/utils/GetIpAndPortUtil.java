package com.zcm.commons.utils;

import com.zcm.commons.config.*;

import javax.servlet.http.*;
import java.net.*;
import java.util.*;

/**
 * @ClassName: GetIpAndPortUtil
 * @Author: FJW
 * @Date: 2021/4/28 15:42
 * @Version: 1.0.0
 * @Description: 获取IP端口帮助类
 */
public class GetIpAndPortUtil {

    private static String LOCAL_IP;
    private static String PATH;

    //获取ip
    public static String getLocalIp() {
        if (LOCAL_IP != null) {
            return LOCAL_IP;
        }
        try {
            OK:
            for (Enumeration<NetworkInterface> interfaces = NetworkInterface.getNetworkInterfaces(); interfaces.hasMoreElements(); ) {
                NetworkInterface networkInterface = interfaces.nextElement();
                if (networkInterface.isLoopback() || networkInterface.isVirtual() || !networkInterface.isUp()) {
                    continue;
                }
                Enumeration<InetAddress> addresses = networkInterface.getInetAddresses();
                while (addresses.hasMoreElements()) {
                    InetAddress address = addresses.nextElement();
                    if (address instanceof Inet4Address) {
                        LOCAL_IP = address.getHostAddress();
                        break OK;
                    }
                }
            }
        } catch (SocketException e) {
            e.printStackTrace();
        }
        if (LOCAL_IP != null) {
            return LOCAL_IP;
        } else {
            LOCAL_IP = "127.0.0.1";
            return LOCAL_IP;
        }
    }

    //通过request获取ip
    public static String getIp() {
        return RequestHolderUtil.getRequest().getServerName();
    }

    //通过request获取端口
    public static int getLocalPort() {
        HttpServletRequest request = RequestHolderUtil.getRequest();
        if (request != null) {
            return request.getServerPort();
        }
        return IdConfig.getPort();
    }


    public static String getIpAndPort() {
        if (PATH != null) {
            return PATH;
        }
        PATH = getLocalIp() + ":" + getLocalPort();
        return PATH;
    }
}
