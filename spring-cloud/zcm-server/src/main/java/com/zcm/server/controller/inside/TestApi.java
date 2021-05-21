package com.zcm.server.controller.inside;
import com.zcm.entity.dto.*;
import org.springframework.web.bind.annotation.*;

/**
 * @ClassName: TestApi
 * @Author: zcm
 * @Date: 2021/4/27 17:32
 * @Version: 1.0.0
 * @Description: 测试接口
 */
@RequestMapping("/test/api")
public interface TestApi {
    @RequestMapping("/testES")
    void testES();

    @RequestMapping("/getUser")
    UserDto getUser(String id);
}
