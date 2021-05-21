package com.zcm.commons.entity;

import com.zcm.commons.enumer.*;

import java.io.*;

/**
 * @ClassName: ReturnSuccess
 * @Author: zcm
 * @Date: 2021/4/28 14:57
 * @Version: 1.0.0
 * @Description: 返回参数
 */
public class ReturnSuccess implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 是否成功
     */
    protected Boolean suc;

    /**
     * 标识
     */
    protected int code;
    /**
     * 消息
     */
    protected String msg;
    /**
     * 返回数据主体
     */
    private Object result;

    public ReturnSuccess() {
    }

    public ReturnSuccess(boolean success, int code) {
        this.suc = success;
        this.code = code;
    }

    public ReturnSuccess(boolean success, int code, Object result) {
        this.suc = success;
        this.code = code;
        this.result = result;
    }

    public ReturnSuccess(boolean success, Object result) {
        this.suc = success;
        this.result = result;
    }

    public ReturnSuccess(ResultEnum resultEnum) {
        this.suc = resultEnum.isSuccess();
        this.code = resultEnum.getErrorCode();
        this.msg = resultEnum.getMessage();
    }

    public ReturnSuccess(ResultEnum resultEnum, Object result) {
        this.suc = resultEnum.isSuccess();
        this.code = resultEnum.getErrorCode();
        this.msg = resultEnum.getMessage();
        this.result = result;
    }

    public ReturnSuccess(ResultEnum resultEnum, String msg) {
        this.suc = resultEnum.isSuccess();
        this.code = resultEnum.getErrorCode();
        this.msg = msg;
    }

    public ReturnSuccess(ResultEnum resultEnum, String msg, Object result) {
        this.suc = resultEnum.isSuccess();
        this.code = resultEnum.getErrorCode();
        this.msg = msg;
        this.result = result;
    }
}
