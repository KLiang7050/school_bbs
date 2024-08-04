package com.bbs.post;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.ApplicationContext;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@EnableFeignClients(basePackages = "com.bbs.api")
@SpringBootApplication()
public class BBSPostApplication {

	public static void main(String[] args) {
		SpringApplication.run(BBSPostApplication.class, args);
	}

}
