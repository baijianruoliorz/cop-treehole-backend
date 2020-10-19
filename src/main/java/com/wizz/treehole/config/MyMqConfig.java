package com.wizz.treehole.config;

import com.wizz.treehole.entity.User;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.Exchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

/**
 * @author liqiqi_tql
 * @date 2020/10/17 -21:11
 */

//这个会自动创建 组件 所以说就当做延时队列的demo使用 不要太当真
@Configuration
public class MyMqConfig {

    @RabbitListener(queues = "order.release.order.queue")
    public void listener(User user){
        System.out.println("收到用户信息"+user.getId());

    }

//    @bean binding
//    死信队列
    /**
     *
     * @param
     * @return 容器中的组件 binding queue exchange 都会自动刚创建
     * @author baijianruoliorz
     * @creed: Talk is cheap,show me the code
     * @date 2020/10/17 21:21
     */
    @Bean
    public Queue orderDelayQueue(){

        Map<String,Object> arguments=new HashMap<>();
        arguments.put("x-dead-letter-exchange","order-event-exchange");
        arguments.put("x-dead-letter-routing-key","order.release.order");
        arguments.put("x-message-tt10",60000);
        Queue queue = new Queue("order.delay.queue", true, false, false, arguments);
        return queue;
    }
    @Bean
    public Queue orderReleaseOrderQueue(){
        Queue queue = new Queue("order.delay.queue", true, false, false);
        return queue;
    }
    @Bean
    public Exchange orderEventExchange(){
    return new TopicExchange("order-event-exchange",true,false);
    }
    @Bean
    public Binding orderCreateOrderBinding(){
        return new Binding("order.delay.queue",Binding.DestinationType.QUEUE,"order-event-exchange"
        ,"order.create.order",null);

    }
    @Bean
    public Binding orderReleaseOrderBinding(){
        return new Binding("order.release.order.queue",Binding.DestinationType.QUEUE,"order-event-exchange"
                ,"order.release.order",null);
    }
}
