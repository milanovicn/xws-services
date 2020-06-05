package com.example.adninservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;



@SpringBootApplication
@EnableFeignClients
@RestController
public class AdninServiceApplication {

	@RequestMapping("/health")
	public String home(){return "Hello";}

	public static void main(String[] args) {
		SpringApplication.run(AdninServiceApplication.class, args);
	}

}
