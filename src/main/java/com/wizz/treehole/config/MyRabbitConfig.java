package com.wizz.treehole.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;

/**
 * @author liqiqi_tql
 * @date 2020/10/17 -17:29
 */
//给容器中放一个消息转换器

@Configuration
public class MyRabbitConfig {
    @Autowired
    RabbitTemplate rabbitTemplate;
    public MessageConverter messageConverter(){
//        可以转成json而不是乱码==
        return new Jackson2JsonMessageConverter();
    }
//   定制rabbitTemplate,postConstruct注解是表示 在这个配置文创建完成后执行这个放大
    @PostConstruct
     public void initRabbitTemplate(){
//设置确认回调,消息发送后就会触发确认回调   这里会报 Only one ConfirmCallback is supported by each RabbitTemplate 因为一个rabbitTemplate只会有一个ConfirmCallback
         rabbitTemplate.setConfirmCallback(new RabbitTemplate.ConfirmCallback() {
             @Override                                            //只要消息抵达代理,ack就等于true broker
             public void confirm(CorrelationData correlationData, boolean ack, String cause) {
                 System.out.println("confirm...correlationData["+correlationData+"]==>ack["+ack+"]==>cause["+cause+"]");
             }
         });
//设置消息的抵达队列的确认回调
        rabbitTemplate.setReturnCallback(new RabbitTemplate.ReturnCallback() {
//            只要消息没有投递给指定的队列,就回调这个
            @Override
            public void returnedMessage(Message message, int replyCode, String replyText, String exchange, String routingKey) {
//                可以指定错误的路由来检测这个方法的可行性.
                System.out.println("Fail MessAge..."+message+"    replyCode:"+replyCode+"    replyText:"+replyText+"    exchange:"+exchange+"    routingKey:"+routingKey);
            }
        });
     }


//     消费端默认是自动确认的,只要接收到,客户端会自动确认,服务端就会移除这个消息
//    问题:我们收到一个消息,但是自动回复了ACK,只有一个消息回复成功,消息丢失了,所以要手动确认
//    手动的确认消息,只要我们没有确认收到消息,消息就不会丢失,等下一个consumer才能
//    签收消息
}
