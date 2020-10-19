package com.wizz.treehole;

import com.wizz.treehole.entity.User;
import com.wizz.treehole.service.UserService;
import org.apache.logging.log4j.Logger;
import org.aspectj.weaver.ast.Var;
import org.junit.jupiter.api.Test;
import org.junit.platform.commons.logging.LoggerFactory;
import org.junit.runner.RunWith;
import org.springframework.amqp.core.AmqpAdmin;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.UUID;

@SpringBootTest
@RunWith(SpringRunner.class)
class TreeholeApplicationTests {

    @Autowired
    AmqpAdmin amqpAdmin;

     @Autowired
     RabbitTemplate rabbitTemplate;
    @Test
    void contextLoads() {

    }
//    创建一个交换机
    @Test
    public void createExchange(){
//        amqpAdmin
        DirectExchange directExchange = new DirectExchange("hello-java-exchange",true,false);
        amqpAdmin.declareExchange(directExchange);
    }
//    创建对列
    @Test
    public void  createQueue(){
        Queue queue=new Queue("hello-java-queue",true,false,false);
        amqpAdmin.declareQueue(queue);
        //logger.info("asd");
    }

    @Test
    public void createBinding(){
//        string destination 目的地
//        destinationType destinationType
//        string exchange
//        string routingKey
//        map<string,object> arguments[自定义参数]
//        将exchange指定的交换机和destination目的地进行绑定,使用routingkey作为指定的路由键
        Binding binding=new Binding("hello-java-queue",Binding.DestinationType.QUEUE,"hello-java-exchange","hello.java",null);

        amqpAdmin.declareBinding(binding);

    }

    @Test
    public void sendMessTest(){
        rabbitTemplate.convertAndSend("hello-java-exchange","hello.java","hello,world!",new CorrelationData(UUID.randomUUID().toString()));
    }

    //User user=new User(45,"liqiqiorz233","asdaasd","手打","sda","dsa","dsa","dsa")
//    可以发送一个对象
    @Test
    public void sendMessageTest(){
        User user=new User(111,"liqiqiorz");
//        可以发送任意类型的数据 对象必须Serializable                                                   //代表我们消息的唯一ID
        rabbitTemplate.convertAndSend("hello-java-exchange","hello.java",user,new CorrelationData(UUID.randomUUID().toString()));
    }

    @Test
    public void uuidDemo(){
        UUID uuid = UUID.randomUUID();
        String string = uuid.toString();
        System.out.println(string);
    }



}
