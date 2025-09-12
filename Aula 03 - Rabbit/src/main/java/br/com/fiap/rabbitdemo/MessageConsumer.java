package br.com.fiap.rabbitdemo;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
public class MessageConsumer {
    @RabbitListener(queues = "minha.queue")
    public void receiveMessage(String message){
        System.out.println("Mensagem recebida: "+message);
    }
}
