package com.zcm.entity.vo;
import com.zcm.entity.po.*;
import lombok.*;
import lombok.experimental.*;

/**
 * @ClassName: UserVo
 * @Author: zcm
 * @Date: 2021/4/26 11:11
 * @Version: 1.0.0
 * @Description: 用户信息展示层数据传输对象
 * 注释：用户发出请求（可能是填写表单），表单的数据在展示层被匹配为VO
 */
@Data
@Accessors
@EqualsAndHashCode(callSuper = false)
public class UserVo extends User {
}
