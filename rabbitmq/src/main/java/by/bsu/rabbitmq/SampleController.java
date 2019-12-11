package by.bsu.rabbitmq;

import org.apache.log4j.Logger;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
public class SampleController {
    @Autowired
    AmqpTemplate template;

    @RequestMapping("/send")
    @ResponseBody
    String queue1() {
        for (int i = 0; i < 5; i++) {
            template.convertAndSend("emails","Message " + i);
        }
        return "Emails send";
    }
}
