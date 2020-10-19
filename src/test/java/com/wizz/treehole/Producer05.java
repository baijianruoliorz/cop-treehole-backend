//import com.wizz.treehole.config.RabbitmqConfig;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.springframework.amqp.rabbit.core.RabbitTemplate;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.test.context.junit4.SpringRunner;
//
//@SpringBootTest
//@RunWith(SpringRunner.class)
//public class Producer05 {
//    @Autowired
//    RabbitTemplate rabbitTemplate;
//    // 使用rabbitTemplate来发送消息
//    @Test
//    public void testSendEmail(){
//        String message = "send email to user";
//        /**
//         * 参数
//         * 1。交换机名称
//         * 2。routingKey
//         * 3。消息内容
//         */
//        rabbitTemplate.convertAndSend(RabbitmqConfig.EXCHANGE_TOPICS_INFORM,"inform.email",message);
//    }
//}