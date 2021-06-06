package aldora.spring.jms.sender;


import aldora.spring.jms.config.JmsConfig;
import aldora.spring.jms.model.HelloWorldMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.UUID;

@RequiredArgsConstructor
@Component
public class HelloSender {
    private final JmsTemplate jmsTemplate;

    @Scheduled(fixedRate = 2000)
    public void sendMessage() {
        System.out.println("I'm sending message");

        HelloWorldMessage message = HelloWorldMessage.builder()
                .id(UUID.randomUUID())
                .message("Hello World")
                .build();

        jmsTemplate.convertAndSend(JmsConfig.MY_QUEUE, message);

        System.out.println("Message sent");
    }
}
