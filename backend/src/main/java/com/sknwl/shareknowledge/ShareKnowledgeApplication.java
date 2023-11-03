package com.sknwl.shareknowledge;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication

@EnableFeignClients
public class ShareKnowledgeApplication {

	public static void main(String[] args) {
		SpringApplication.run(ShareKnowledgeApplication.class, args);
	}

}
