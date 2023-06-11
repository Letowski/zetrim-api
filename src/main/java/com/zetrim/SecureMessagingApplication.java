package com.zetrim;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

import java.util.TimeZone;

@SpringBootApplication

@EnableFeignClients(basePackages = {"com.zetrim"})
public class SecureMessagingApplication {

	public static void main(String[] args) {
		TimeZone.setDefault(TimeZone.getTimeZone("GMT0:00"));
		SpringApplication.run(SecureMessagingApplication.class, args);
	}
}
