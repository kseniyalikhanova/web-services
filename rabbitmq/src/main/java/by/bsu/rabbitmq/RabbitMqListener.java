package by.bsu.rabbitmq;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RabbitMqListener {
    @Autowired
    private EmailService emailService;

    @RabbitListener(queues = "emails")
    public void listen(String message) {
        emailService.send("kseniya.likhanova@gmail.com", "Test email", message);
    }
}
