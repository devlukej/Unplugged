package com.example.unplugged;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class UnpluggedApplication {

	public static void main(String[] args) {
		SpringApplication.run(UnpluggedApplication.class, args);
	}

}
