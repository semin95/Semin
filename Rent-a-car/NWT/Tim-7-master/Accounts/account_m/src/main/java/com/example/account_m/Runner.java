package com.example.account_m;

import com.example.account_m.entity.Client;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

import java.util.Arrays;


@EnableDiscoveryClient
@SpringBootApplication
public class Runner 
{

	public static void main(String[] args) {
		SpringApplication.run(Runner.class, args);
	}
}
