package com.zcm.entity.dto;

import lombok.*;

import java.io.*;

/**
 * @program: spring-cloud
 * @ClassName MsgEntity
 * @Description
 * @Author zcm
 * @Date 2021/5/27 15:10
 * @Version V1.0
 */
@Data
public class MsgEntity implements Serializable {
    private String msgId;
    private String userId;
    private String mobile;
    private String email;

    public MsgEntity() {
    }

    public MsgEntity(String msgId, String userId, String mobile, String email) {
        this.msgId = msgId;
        this.userId = userId;
        this.mobile = mobile;
        this.email = email;
    }
}
