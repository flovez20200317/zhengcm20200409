package com.zcm.server.service;

import com.baomidou.mybatisplus.extension.service.*;
import com.zcm.entity.dto.*;
import com.zcm.entity.po.*;
import com.zcm.entity.vo.*;

/**
 * @ClassName: UserService
 * @Author: zcm
 * @Date: 2021/4/27 17:39
 * @Version: 1.0.0
 * @Description: 用户信息服务层
 */
public interface UserService extends IService<User> {
    /**
     * 功能描述: <br>
     * 根据用户ID 查询用户信息
     *
     * @return com.xc.commons.entity.dto.UserDto
     * @Author: zcm
     * @Version: 1.0.0
     * @Param: vo
     * @Date: 2021/4/26 11:52
     */
    UserDto queryUserByid(UserVo vo);
}
