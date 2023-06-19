package com.example.challenge_backend;

import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@EnableRabbit
@SpringBootApplication
public class ChallengeBackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(ChallengeBackendApplication.class, args);
	}

}
