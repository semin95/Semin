package com.example.vehicle;

import java.util.concurrent.TimeUnit;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class Runner  implements CommandLineRunner {

    private final RabbitTemplate rabbitTemplate;
    private final Reciver receiver;

    public Runner(Reciver receiver, RabbitTemplate rabbitTemplate) {
        this.receiver = receiver;
        this.rabbitTemplate = rabbitTemplate;
    }

    @Override
    public void run(String... args) throws Exception {
        System.out.println("Sending message...");
        rabbitTemplate.convertAndSend(VehicleApplication.topicExchangeName, "com.example.orders.reserve", "5");
        receiver.getLatch().await(10000, TimeUnit.MILLISECONDS);
    }

}
