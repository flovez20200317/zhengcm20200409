package com.zcm.server.config;

import org.springframework.amqp.core.Queue;
import org.springframework.beans.factory.annotation.*;
import org.springframework.context.annotation.*;
import org.springframework.amqp.core.*;

import java.util.*;

/**
 * @program: spring-cloud
 * @ClassName：RabbitConfig
 * @Description：
 * @Author：zcm
 * @Date：2021/5/21 10:37
 */
@Configuration
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
    /************************************扇形交换机开始******************************************/
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
     * 1、创建队列
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
        //绑定备胎队列
        return new Queue(FANOUT_QUEUE_NAME_EMAIL);
    }

    /**
     * 2、创建交换机
     */


    @Bean
    public FanoutExchange fanoutExchange() {
        return new FanoutExchange(FANOUT_EXCHANGE_NAME);
    }


    /**
     * 3、将队列绑定到交换机
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

    /************************************扇形交换机结束******************************************/
    /************************************路由交换机******************************************/
    /**
     * 订单交换机
     */
    @Value("${zcm.order.exchange}")
    private String orderExchange;

    /**
     * 订单队列
     */
    @Value("${zcm.order.queue}")
    private String orderQueue;

    /**
     * 订单路由key
     */
    @Value("${zcm.order.routingKey}")
    private String orderRoutingKey;
    /**
     * 死信交换机
     */
    @Value("${zcm.dlx.exchange}")
    private String dlxExchange;

    /**
     * 死信队列
     */
    @Value("${zcm.dlx.queue}")
    private String dlxQueue;
    /**
     * 死信路由
     */
    @Value("${zcm.dlx.routingKey}")
    private String dlxRoutingKey;

    /**
     * 声明死信交换机
     *
     * @return DirectExchange
     */
    @Bean
    public DirectExchange dlxExchange() {
        return new DirectExchange(dlxExchange);
    }

    /**
     * 声明死信队列
     *
     * @return Queue
     */
    @Bean
    public Queue dlxQueue() {
        return new Queue(dlxQueue);
    }

    /**
     * 声明订单业务交换机
     *
     * @return DirectExchange
     */
    @Bean
    public DirectExchange orderExchange() {
        return new DirectExchange(orderExchange);
    }

    @Bean
    public Queue orderQueue() {
        //绑定备胎队列
        Map<String, Object> args = new HashMap<>(2);
        args.put("x-dead-letter-exchange", dlxExchange);
        args.put("x-dead-letter-routing-key", dlxRoutingKey);
        args.put("x-message-ttl", 10000);
        return new Queue(orderQueue, true, false, false, args);
    }

    /**
     * 绑定死信队列到死信交换机
     *
     * @return Binding
     */
    @Bean
    public Binding binding() {
        return BindingBuilder.bind(dlxQueue())
                .to(dlxExchange())
                .with(dlxRoutingKey);
    }


    /**
     * 绑定订单队列到订单交换机
     *
     * @return Binding
     */
    @Bean
    public Binding orderBinding() {
        return BindingBuilder.bind(orderQueue())
                .to(orderExchange())
                .with(orderRoutingKey);
    }

}
