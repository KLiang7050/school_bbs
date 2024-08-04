package com.bbs.like;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@EnableFeignClients(basePackages = "com.bbs.api")
@SpringBootApplication()
public class BBSLikeApplication {

	public static void main(String[] args) {
		SpringApplication.run(BBSLikeApplication.class, args);
	}

}
