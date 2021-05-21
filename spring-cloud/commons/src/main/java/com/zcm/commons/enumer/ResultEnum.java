package com.zcm.commons.enumer;

/**
 * @ClassName: ResultEnum
 * @Author: FJW
 * @Date: 2021/4/28 14:58
 * @Version: 1.0.0
 * @Description: 返回数据枚举统一封装
 */
public enum ResultEnum {
    /**
     * 默认成功
     */
    SUCCESS_DEFAULT(true, 200, "success"),

    /**
     * 默认失败
     */
    ERROR_DEFAULT(false, 999, "网络开小差了，请稍后再试"),

    /**
     * 统一处理异常
     *
     * @Author sfh
     * @Date 2019/12/26 17:12
     */
    ERROR_COMMON_DEFAULT(false, 1000, "网络开小差了，请稍后再试"),

    /**
     * 3xxx  服务异常
     * 服务处理异常
     */
    ERROR_NULL_DATA(false, 3000, "未找到数据"),
    ERROR_DELETE_DATA(false, 3001, "数据异常"),
    ERROR_NOT_LOGIN(false, 4000, "未登录"),

    /**
     * 5xxx 请求异常
     */
    ERROR_EXCEPTION(false, 5000, "异常"),
    ERROR_FAILURE(false, 5001, "失败"),
    ERROR_REPEAT(false, 5002, "重复"),
    ERROR_DATA_EXPIRE(false, 5010, "数据验证不通过"),
    ERROR_THIRD_PLATFORM(false, 5020, "第三方平台错误信息"),

    /**
     * 自定义异常
     */
    ERROR_LIMIT_EXCEEDED(false, 6000, "商品图片添加数量超出上限，指定使用"),
    ERROR_BUSINESS_ERROR(false, 7000, "默认业务性异常 "),
    ERROR_BLACK_LIST(false, 8000, "该用户被加入黑名单，无法使用程序"),
    ERROR_KICK_OUT(false, 9000, "您的账号登录终端超过限制，本机已下线"),
    ERROR_KICK_LOGIN_OUT(false, 9001, "您的VIP过期或者关闭了手机账号登录，自动退出登录"),
    ERROR_KICK_FF_LOGIN_OUT(false, 9002, "token验证未通过，非法请求，自动退出登录");

    private boolean isSuccess;
    private int errorCode;
    private String message;

    ResultEnum(boolean isSuccess, int errorCode, String message) {
        this.isSuccess = isSuccess;
        this.errorCode = errorCode;
        this.message = message;
    }

    public boolean isSuccess() {
        return isSuccess;
    }

    public int getErrorCode() {
        return errorCode;
    }

    public String getMessage() {
        return message;
    }
}
