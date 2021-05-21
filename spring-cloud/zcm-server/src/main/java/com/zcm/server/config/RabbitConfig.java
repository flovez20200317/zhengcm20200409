package com.zcm.server.config;

import org.springframework.context.annotation.*;
import org.springframework.stereotype.*;
import org.springframework.amqp.core.*;

/**
 * @program: spring-cloud
 * @ClassName：RabbitConfig
 * @Description：
 * @Author：zcm
 * @Date：2021/5/21 10:37
 */
@Component
public class RabbitConfig {

    /**
     * Broker:它提供一种传输服务,它的角色就是维护一条从生产者到消费者的路线，保证数据能按照指定的方式进行传输,
     * Exchange：消息交换机,它指定消息按什么规则,路由到哪个队列。
     * Queue:消息的载体,每个消息都会被投到一个或多个队列。
     * Binding:绑定，它的作用就是把exchange和queue按照路由规则绑定起来.
     * Routing Key:路由关键字,exchange根据这个关键字进行消息投递。
     * vhost:虚拟主机,一个broker里可以有多个vhost，用作不同用户的权限分离。
     * Producer:消息生产者,就是投递消息的程序.
     * Consumer:消息消费者,就是接受消息的程序.
     * Channel:消息通道,在客户端的每个连接里,可建立多个channel.
     */

    public static final String PREFIX = "zcm.cloud.";
    /**
     * @Author: zcm
     * @Description:交换机名称
     */
    public static final String FANOUT_EXCHANGE_NAME = PREFIX + "fanout.exchange";
    /**
     * @Author: zcm
     * @Description:短信队列名称
     */
    public static final String FANOUT_QUEUE_NAME_SMS = PREFIX + "fanout.queue.sms";

    /**
     * @Author: zcm
     * @Description:邮件队列
     */
    public static final String FANOUT_QUEUE_NAME_EMAIL = PREFIX + "fanout.queue.email";

    /**
     * 将队列和交换机注入spring容器中
     *
     * @return
     */
    @Bean
    public Queue smsQueue() {
        return new Queue(FANOUT_QUEUE_NAME_SMS);
    }

    @Bean
    public Queue emailQueue() {
        return new Queue(FANOUT_QUEUE_NAME_EMAIL);
    }

    @Bean
    public FanoutExchange fanoutExchange() {
        return new FanoutExchange(FANOUT_EXCHANGE_NAME);
    }


    /**
     * 2.关联交换机
     * 方式一:根据参数名称 ioc获取Queue 对象
     *
     * @return
     */
    @Bean
    public Binding binDingSMSFanoutExchange(Queue smsQueue, FanoutExchange fanoutExchange) {
        return BindingBuilder.bind(smsQueue).to(fanoutExchange);
    }

    @Bean
    public Binding binDingEmailFanoutExchange(Queue emailQueue, FanoutExchange fanoutExchange) {
        return BindingBuilder.bind(emailQueue).to(fanoutExchange);
    }
}
