package by.bsu.rabbitmq;

import org.apache.log4j.Logger;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Random;

@Component
public class RabbitMqListener {
    @Autowired
    private EmailService emailService;

    @RabbitListener(queues = "emails")
    public void consumer(String message) {
        emailService.send("kseniya.likhanova@gmail.com", "Test email", message);
    }
}
