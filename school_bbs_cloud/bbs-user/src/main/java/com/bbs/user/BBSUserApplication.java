package com.bbs.user;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.openfeign.EnableFeignClients;


@EnableFeignClients(basePackages = "com.bbs.api")
@SpringBootApplication()
public class BBSUserApplication {

	public static void main(String[] args) {
		SpringApplication.run(BBSUserApplication.class, args);
	}

}
