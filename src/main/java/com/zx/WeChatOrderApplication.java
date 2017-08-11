package com.zx;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class WeChatOrderApplication {

	public static void main(String[] args) {
		SpringApplication.run(WeChatOrderApplication.class, args);
	}
}
