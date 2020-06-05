package com.example.voziloservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@EnableFeignClients
@RestController
public class VoziloServiceApplication {

	@RequestMapping("/health")
	public String home(){return "Hello fom car service";}
	public static void main(String[] args) {
		SpringApplication.run(VoziloServiceApplication.class, args);
	}

}
