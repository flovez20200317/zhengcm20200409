package com.zcm.mapper;
import com.baomidou.mybatisplus.core.mapper.*;
import com.zcm.entity.dto.*;
import com.zcm.entity.po.*;
import com.zcm.entity.vo.*;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper extends BaseMapper<User> {

    /**
     * 功能描述: <br>
     * 根据用户ID 查询用户信息
     * @Author: zcm
     * @Version: 1.0.0
     * @Param: vo
     * @return com.xc.commons.entity.dto.UserDto
     * @Date: 2021/4/26 11:52
     */
    UserDto queryUserById(UserVo vo);
}
