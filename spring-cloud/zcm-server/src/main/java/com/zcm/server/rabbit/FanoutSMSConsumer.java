package com.zcm.server.rabbit;

import com.alibaba.fastjson.*;
import com.zcm.entity.dto.*;
import com.zcm.server.config.*;
import lombok.extern.slf4j.*;
import org.springframework.amqp.rabbit.annotation.*;
import org.springframework.stereotype.*;

/**
 * @program: spring-cloud
 * @ClassName FanoutSMSConsumer
 * @Description
 * @Author zcm
 * @Date 2021/5/27 17:02
 * @Version V1.0
 */
@Slf4j
@Component
@RabbitListener(queues = RabbitConfig.FANOUT_QUEUE_NAME_SMS)
public class FanoutSMSConsumer {

    @RabbitHandler
    public void handleFanoutSMSConsumer(MsgEntity msgEntity) {
        log.info("短信消费者接收对象：{}" + JSON.toJSONString(msgEntity));
    }
}
