package com.zcm.server.service.impl;
import com.baomidou.mybatisplus.extension.service.impl.*;
import com.zcm.entity.dto.*;
import com.zcm.entity.po.*;
import com.zcm.entity.vo.*;
import com.zcm.mapper.*;
import com.zcm.server.service.*;
import org.springframework.stereotype.*;

/**
 * @ClassName: UserServiceImpl
 * @Author: FJW
 * @Date: 2021/4/27 17:40
 * @Version: 1.0.0
 * @Description: 用户信息接口服务层实现
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {
    
    @Override
    public UserDto queryUserByid(UserVo vo) {
        return baseMapper.queryUserById(vo);
    }
}
