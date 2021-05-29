package com.zcm.server.rabbit;

import com.alibaba.fastjson.*;
import com.rabbitmq.client.*;
import com.zcm.entity.dto.*;
import com.zcm.server.config.*;
import lombok.*;
import lombok.extern.slf4j.*;
import org.springframework.amqp.core.*;
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

public class OrderConsumer {

    @SneakyThrows
    @RabbitListener(queues = "zcm_order_queue")
    public void handleMsg(MsgEntity msgEntity, Channel channel, Message message) {
        log.info("路由：《正常》短信消费者接收对象：{}", JSON.toJSONString(msgEntity));
        //拒绝消息消息（丢失消息给）给死信队列处理
        channel.basicNack(message.getMessageProperties().getDeliveryTag(), false, false);
    }
}
