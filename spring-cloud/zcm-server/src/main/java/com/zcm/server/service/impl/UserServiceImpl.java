package com.zcm.server.service.impl;

import com.alibaba.fastjson.*;
import com.baomidou.mybatisplus.extension.service.impl.*;
import com.zcm.entity.dto.*;
import com.zcm.entity.po.*;
import com.zcm.entity.vo.*;
import com.zcm.mapper.*;
import com.zcm.server.config.*;
import com.zcm.server.service.*;
import org.springframework.amqp.core.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.*;

import java.util.*;

/**
 * @ClassName: UserServiceImpl
 * @Author: zcm
 * @Date: 2021/4/27 17:40
 * @Version: 1.0.0
 * @Description: 用户信息接口服务层实现
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    @Autowired
    AmqpTemplate amqpTemplate;
    /**
     * 订单交换机
     */
    @Value("${zcm.order.exchange}")
    private String orderExchange;
    /**
     * 订单路由key
     */
    @Value("${zcm.order.routingKey}")
    private String orderRoutingKey;

    @Override
    public UserDto queryUserByid(UserVo vo) {
        return baseMapper.queryUserById(vo);
    }

    @Override
    public void sendMsg() throws Exception {
        MsgEntity msgEntity = new MsgEntity(UUID.randomUUID().toString(), "666", "15806043989", "779986181@163.com");
//        MessageProperties messageProperties = new MessageProperties();
//        messageProperties.setExpiration("10000"); // 设置过期时间，单位：毫秒
//        Message message = new Message(JSON.toJSONString(msgEntity).getBytes(), messageProperties);
//
        /**
         * 1.交换机名称
         * 2.路由key名称
         * 3.发送内容
         */
        amqpTemplate.convertAndSend(orderExchange, orderRoutingKey, msgEntity);
        System.out.println("发送成功");

    }
}
