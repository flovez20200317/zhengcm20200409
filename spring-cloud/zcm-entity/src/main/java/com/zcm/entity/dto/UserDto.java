package com.zcm.entity.dto;
import com.zcm.entity.po.*;
import lombok.*;
import lombok.experimental.*;

/**
 * @ClassName: UserDto
 * @Author: zcm
 * @Date: 2021/4/26 11:08
 * @Version: 1.0.0
 * @Description: 用户信息 数据传输对象
 * 注释：展示层把VO转换为服务层对应方法所要求的DTO，传送给服务层
 */
@Data
@Accessors
@EqualsAndHashCode(callSuper = false)
public class UserDto extends User {

    //粉丝id
    private String fanId;
}
