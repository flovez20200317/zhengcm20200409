package com.zcm.commons.entity;

import com.zcm.commons.enumer.*;
import lombok.*;

import java.io.*;

/**
 * @ClassName: ReturnMessage
 * @Author: FJW
 * @Date: 2021/4/28 14:57
 * @Version: 1.0.0
 * @Description: 返回参数
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class ReturnMessage extends ReturnSuccess implements Serializable {
    private static final long serialVersionUID = -3440913425989932526L;

    /**
     * 返回数据主体
     */
    private Object result;

    public ReturnMessage() {
    }

    public ReturnMessage(int code, String msg, Object result) {

        this.code = code;
        this.msg = msg;
        this.result = result;
    }

    public ReturnMessage(ResultEnum resultEnum, String msg, Object result) {

        this.code = resultEnum.getErrorCode();
        if (msg == null) {
            this.msg = resultEnum.getMessage();
        } else {
            this.msg = msg;
        }
        this.result = result;
    }

    public ReturnMessage(ResultEnum resultEnum, Object result) {

        this.code = resultEnum.getErrorCode();
        this.msg = resultEnum.getMessage();
        this.result = result;
    }

    public ReturnMessage(int code) {
        this.code = code;
    }
}
