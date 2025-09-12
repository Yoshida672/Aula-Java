package br.com.fiap.rabbitdemo;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MessageProducer {
@Autowired
    private RabbitTemplate rabbitTemplate;
public void sendMessage(String message){
    rabbitTemplate.convertAndSend("meu.exchange","minha.routingKey",message);
}
}
