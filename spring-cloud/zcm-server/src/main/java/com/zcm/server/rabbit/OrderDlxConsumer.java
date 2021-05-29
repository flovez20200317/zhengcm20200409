package com.zcm.server.rabbit;

import com.alibaba.fastjson.*;
import com.rabbitmq.client.*;
import com.zcm.entity.dto.*;
import lombok.extern.slf4j.*;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.annotation.*;
import org.springframework.stereotype.*;

import java.io.*;

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
public class OrderDlxConsumer {

    @RabbitListener(queues = "zcm_order_dlx_queue")
    public void handleMsg(MsgEntity msgEntity, Channel channel, Message message) {
        log.info("路由：<<死信>>短信消费者接收对象：{}", JSON.toJSONString(msgEntity));
        try {

            //手动确认消费消息
            channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
