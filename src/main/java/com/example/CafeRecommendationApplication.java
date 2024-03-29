package com.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@SpringBootApplication
public class CafeRecommendationApplication {

	public static void main(String[] args) {
		SpringApplication.run(CafeRecommendationApplication.class, args);
	}
	

}
