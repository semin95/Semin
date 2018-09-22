package com.example.account_m.async;

import org.springframework.stereotype.Component;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;


@Component
public class Publisher
{
	private final AmqpTemplate amqpTemplate;

    @Value ("${jsa.rabbitmq.exchange}")
    private String exchange;

    @Autowired
    public Publisher(AmqpTemplate amqpTemplate)
    {
        this.amqpTemplate = amqpTemplate;
    }

    public void produceMsg(String msg)
    {
        amqpTemplate.convertAndSend(exchange, "",msg);
        System.out.println("Send msg = " + msg);
    }
}
	
