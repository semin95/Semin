package com.etf.ppis.lambda.telenash;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication
@EntityScan (basePackages = {"com.etf.ppis.lambda.telenash.model"})
public class TelenashApplication
{
    public static void main(String[] args)
    {
        SpringApplication.run(TelenashApplication.class, args);
    }
}