package com.zcm.commons.utils;

import org.springframework.web.context.request.*;

import javax.servlet.http.*;

/**
 * @ClassName: RequestHolderUtil
 * @Author: FJW
 * @Date: 2021/4/28 15:36
 * @Version: 1.0.0
 * @Description: 请求处理类
 */
public class RequestHolderUtil {

    private static RequestAttributes getRequestAttributes() {
        RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
        return requestAttributes;
    }

    public static HttpServletRequest getRequest() {
        RequestAttributes requestAttributes = getRequestAttributes();
        if (requestAttributes == null) {
            return null;
        }

        HttpServletRequest req = ((ServletRequestAttributes) requestAttributes).getRequest();
        return req;
    }

    public static HttpServletResponse getResponse() {
        RequestAttributes requestAttributes = getRequestAttributes();
        if (requestAttributes == null) {
            return null;
        }
        HttpServletResponse resp = ((ServletWebRequest) requestAttributes).getResponse();
        return resp;
    }

    public static HttpSession getSession() {
        RequestAttributes requestAttributes = getRequestAttributes();
        if (requestAttributes == null) {
            return null;
        }
        return ((ServletRequestAttributes) requestAttributes).getRequest().getSession(true);
    }

    public static HttpSession getSession(boolean create) {
        RequestAttributes requestAttributes = getRequestAttributes();
        if (requestAttributes == null) {
            return null;
        }
        return ((ServletRequestAttributes) requestAttributes).getRequest().getSession(create);
    }

    public static String getRequestHeader(String head) {
        RequestAttributes requestAttributes = getRequestAttributes();
        if (requestAttributes == null) {
            return null;
        }
        HttpServletRequest req = ((ServletRequestAttributes) requestAttributes).getRequest();
        return req.getHeader(head);
    }
}
