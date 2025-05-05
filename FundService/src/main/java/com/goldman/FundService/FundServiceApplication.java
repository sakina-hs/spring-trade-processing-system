package com.goldman.FundService;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class FundServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(FundServiceApplication.class, args);
	}

}
