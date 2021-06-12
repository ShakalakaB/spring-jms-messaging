package aldora.spring.jms.listener;

import aldora.spring.jms.config.JmsConfig;
import aldora.spring.jms.model.HelloWorldMessage;
import lombok.AllArgsConstructor;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.handler.annotation.Headers;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import java.util.UUID;

@Component
@AllArgsConstructor
public class HelloMessageListener {
    private final JmsTemplate jmsTemplate;

    @JmsListener(destination = JmsConfig.MY_QUEUE)
    public void listen(@Payload HelloWorldMessage helloWorldMessage,
                       @Headers MessageHeaders headers) {
//        System.out.println("I got a message");
//        System.out.println(helloWorldMessage);

//        throw new RuntimeException("test");
    }

    @JmsListener(destination = JmsConfig.MY_SEND_RCV_QUEUE)
    public void listenAndReply(@Payload HelloWorldMessage helloWorldMessage,
                               @Headers MessageHeaders headers, Message message,
                               org.springframework.messaging.Message springMessage) throws JMSException {
        HelloWorldMessage payloadMsg = HelloWorldMessage
                .builder()
                .id(UUID.randomUUID())
                .message("I hear you!!")
                .build();

//        jmsTemplate.convertAndSend(message.getJMSReplyTo(), payloadMsg);

        //example to use Spring Message type
        jmsTemplate.convertAndSend((Destination) springMessage.getHeaders().get("jms_replyTo"), "Got it through spring message");

    }
}
