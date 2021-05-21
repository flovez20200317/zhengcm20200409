package com.zcm.commons.utils;

import com.zcm.commons.entity.*;
import com.zcm.commons.enumer.*;

/**
 * 新版返回帮助封装
 * 生成返回体统一封装类
 *
 * @ClassName:
 * @author: SFH
 * @date: 2019/7/25  17:08
 */
public class ReturnMessageUtil {
    /**
     * 内嵌返回状态
     * v2.0.1
     *
     * @param success 成功/失败
     * @param code    对应弹窗标号
     * @return
     * @Author sfh
     * @Date 2020/8/10 11:24
     * @version:
     */
    public static ReturnMessage status(boolean success, int code) {
        return new ReturnMessage(ResultEnum.SUCCESS_DEFAULT, new ReturnSuccess(success, code));
    }

    /**
     * v2.0.1
     * <p>
     * 内嵌返回状态-默认枚举
     *
     * @param resultEnum
     * @return
     * @Author sfh
     * @Date 2020/8/11 15:20
     * @version:
     */
    public static ReturnMessage status(ResultEnum resultEnum) {
        return new ReturnMessage(ResultEnum.SUCCESS_DEFAULT, new ReturnSuccess(resultEnum));
    }

    public static ReturnMessage status(ResultEnum resultEnum, String msg) {
        return new ReturnMessage(ResultEnum.SUCCESS_DEFAULT, new ReturnSuccess(resultEnum, msg));
    }

    public static ReturnMessage status(ResultEnum resultEnum, String msg, Object result) {
        return new ReturnMessage(ResultEnum.SUCCESS_DEFAULT, new ReturnSuccess(resultEnum, msg, result));
    }

    public static ReturnMessage status(ResultEnum resultEnum, Object result) {
        return new ReturnMessage(ResultEnum.SUCCESS_DEFAULT, new ReturnSuccess(resultEnum, result));
    }

    /**
     * 正常返回,默认消息
     *
     * @Author sfh
     * @Date 2019/7/30 14:54
     */
    public static ReturnMessage success() {
        return new ReturnMessage(ResultEnum.SUCCESS_DEFAULT, null);
    }

    /**
     * 正常返回自定义消息
     *
     * @Author sfh
     * @Date 2019/7/30 14:54
     */
    public static ReturnMessage success(String msg) {
        return new ReturnMessage(ResultEnum.SUCCESS_DEFAULT, msg, null);
    }

    /**
     * 使用默认消息，返回详细信息
     *
     * @Author sfh
     * @Date 2019/7/30 14:54
     */
    public static ReturnMessage success(Object result) {
        return new ReturnMessage(ResultEnum.SUCCESS_DEFAULT, null, result);
    }

    /**
     * 成功
     * 返回结果集
     *
     * @Author sfh
     * @Date 2019/7/26 10:22
     */
    public static ReturnMessage success(String msg, Object result) {
        return new ReturnMessage(ResultEnum.SUCCESS_DEFAULT, msg, result);
    }

    /**
     * 成功 ，使用枚举不支持自定义消息
     * 请使用含有SUCCESS_DEFAULT枚举
     *
     * @Author sfh
     * @Date 2019/7/26 10:22
     */
    public static ReturnMessage success(ResultEnum resultEnum, Object result) {
        return new ReturnMessage(resultEnum, result);
    }

    /**
     * 成功 ，使用枚举支持自定义消息
     * 请使用含有SUCCESS_DEFAULT枚举
     *
     * @Author sfh
     * @Date 2019/7/26 10:22
     */
    public static ReturnMessage success(ResultEnum resultEnum, String msg, Object result) {
        return new ReturnMessage(resultEnum, msg, result);
    }


    /**
     * 失败，使用枚举 默认消息
     *
     * @Author sfh
     * @Date 2019/7/31 10:12
     */
    public static ReturnMessage fail() {
        return new ReturnMessage(ResultEnum.ERROR_DEFAULT, null);
    }

    /**
     * 失败，使用枚举 自定义消息
     *
     * @Author sfh
     * @Date 2019/8/23 10:18
     */
    public static ReturnMessage fail(String msg) {
        return new ReturnMessage(ResultEnum.ERROR_DEFAULT, msg);
    }

    /**
     * 失败，使用默认错误枚举
     *
     * @Author sfh
     * @Date 2019/7/26 10:22
     */
    public static ReturnMessage fail(Object result) {
        return new ReturnMessage(ResultEnum.ERROR_DEFAULT, result);
    }

    /**
     * 失败，使用带消息枚举
     *
     * @Author sfh
     * @Date 2019/11/21 17:59
     */
    public static ReturnMessage fail(ResultEnum resultEnum) {
        return new ReturnMessage(resultEnum, null);
    }

    /**
     * 失败，使用枚举 自定义消息
     *
     * @Author sfh
     * @Date 2019/7/31 10:12
     */
    public static ReturnMessage fail(ResultEnum resultEnum, String message) {
        return new ReturnMessage(resultEnum, message);
    }

    /**
     * 失败，使用枚举不支持自定义消息
     * 请使用含有ERROR的枚举
     *
     * @Author sfh
     * @Date 2019/7/26 10:22
     */
    public static ReturnMessage fail(ResultEnum resultEnum, Object result) {
        return new ReturnMessage(resultEnum, result);
    }

    /**
     * 失败，使用枚举支持自定义消息
     * 请使用含有ERROR的枚举
     *
     * @Author sfh
     * @Date 2019/7/26 10:22
     */
    public static ReturnMessage fail(ResultEnum resultEnum, String msg, Object result) {
        return new ReturnMessage(resultEnum, msg, result);
    }

    /**
     * 失败
     * 返回错误信息
     *
     * @Author sfh
     * @Date 2019/7/26 10:22
     */
    public static ReturnMessage fail(int errorCode, String msg, Object result) {
        return new ReturnMessage(errorCode, msg, result);
    }


}
