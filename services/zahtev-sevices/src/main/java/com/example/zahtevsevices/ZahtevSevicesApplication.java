package com.example.zahtevsevices;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@EnableFeignClients
@EnableScheduling
@RestController
public class ZahtevSevicesApplication {

	@RequestMapping("/health")
	public String home(){return "Hello fom zahtev service";}

	public static void main(String[] args) {
		SpringApplication.run(ZahtevSevicesApplication.class, args);
	}

}
