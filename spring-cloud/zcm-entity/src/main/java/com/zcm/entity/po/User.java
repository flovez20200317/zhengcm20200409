package com.zcm.entity.po;


import com.baomidou.mybatisplus.annotation.*;
import com.baomidou.mybatisplus.extension.activerecord.*;
import lombok.*;
import lombok.experimental.*;

import java.io.*;
import java.util.*;

/**
 * @ClassName: User
 * @Author: zcm
 * @Date: 2021/4/26 10:52
 * @Version: 1.0.0
 * @Description: 相册 用户信息
 * 注释：服务层把DO转换为持久层对应的PO（可以使用ORM工具，也可以不用），调用持久层的持久化方法，把PO传递给它，完成持久化操作。
 */
@Data
@Accessors
@TableName("zx_user")
@EqualsAndHashCode(callSuper = false)
public class User extends Model<User> implements Serializable {
    protected static final long serialVersionUID = 1L;

    //用户ID
    @TableId("id")
    protected String id;

    //店铺ID
    @TableField("shop_id")
    protected String shopId;

    //名称
    @TableField("name")
    protected String name;

    //头像
    @TableField("head_url")
    protected String headUrl;

    //性别
    @TableField("sex")
    protected String sex;

    //微信昵称
    @TableField("wechat_nick_name")
    protected String wechatNickName;

    //微信公众号openid
    @TableField("wechat_open_id")
    protected String wechatOpenId;

    //微信小程序openid
    @TableField("wechat_mini_open_id")
    protected String wechatMiniOpenId;

    //微信公众号unionid
    @TableField("wechat_union_id")
    protected String wechatUnionId;

    //国家
    @TableField("country")
    protected String country;

    //城市
    @TableField("city")
    protected String city;

    //省市
    @TableField("province")
    protected String province;

    //账号-手机号
    @TableField("account")
    protected String account;

    //绑定手机号
    @TableField("act_account")
    protected Integer actAccount;

    //MD5加盐
    @TableField("pwd")
    protected String pwd;

    //创建时间
    @TableField("create_time")
    protected Date createTime;

    //修改时间
    @TableField("update_time")
    protected Date updateTime;

    //绑定时间
    @TableField("bind_time")
    protected Date bindTime;

    //app端openid
    @TableField("wechat_app_open_id")
    protected String wechatAppOpenId;

    //终端类型
    @TableField("terminal_type")
    protected Integer terminalType;

    //0 正式用户 -1测试用户
    @TableField("type")
    protected Integer type;

    //账号登录开关
    @TableField("account_switch")
    protected Boolean accountSwitch;

    // 地区
    @TableField("district")
    protected String district;

    //名片地址
    @TableField("address")
    protected String address;

    //账户（手机号） 长度不等于11强制置空
    public String getAccount() {
        return account;
    }

    @Override
    protected Serializable pkVal() {
        return this.id;
    }
}
