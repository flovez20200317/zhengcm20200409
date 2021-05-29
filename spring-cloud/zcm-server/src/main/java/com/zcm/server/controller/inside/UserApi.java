package com.zcm.server.controller.inside;
import org.springframework.web.bind.annotation.*;

/**
 * @ClassName: TestApi
 * @Author: zcm
 * @Date: 2021/4/27 17:32
 * @Version: 1.0.0
 * @Description: 测试接口
 */
@RequestMapping("/user")
public interface UserApi {


    @RequestMapping("/send/msg")
    void sendMsg();

}
