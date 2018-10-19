package com.belatrixsf.findpatternapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

//@SpringBootApplication(exclude = { MongoAutoConfiguration.class, MongoDataAutoConfiguration.class })

@SpringBootApplication
public class FindPatternApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(FindPatternApiApplication.class, args);
	}
}
