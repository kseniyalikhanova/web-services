package by.bsu.rabbitmq;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
public class EmailController {
    @Autowired
    AmqpTemplate template;

    @RequestMapping("/send")
    @ResponseBody
    public String sendEmail() {
        for (int i = 0; i < 5; i++) {
            template.convertAndSend("emails","Message " + i);
        }
        return "Emails send";
    }
}
