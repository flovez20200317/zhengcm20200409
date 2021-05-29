package com.zcm.server.controller.inside.impl;

import com.zcm.server.controller.inside.*;
import com.zcm.server.service.*;
import lombok.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.web.bind.annotation.*;

/**
 * @program: spring-cloud
 * @ClassName UserProvider
 * @Description
 * @Author zcm
 * @Date 2021/5/27 15:17
 * @Version V1.0
 */
@RestController
public class UserProvider implements UserApi {
    @Autowired
    private UserService userService;

    @SneakyThrows
    @Override
    public void sendMsg() {
        userService.sendMsg();
    }
}
