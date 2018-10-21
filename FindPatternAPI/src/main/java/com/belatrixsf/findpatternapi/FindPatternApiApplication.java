package com.belatrixsf.findpatternapi;

import java.util.concurrent.Executor;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

/**
 * 
 * @author Alexander Chiran paulo.alexander12@gmail.com
 * 
 *
 */
@SpringBootApplication
@EnableAsync
public class FindPatternApiApplication {

	public static void main(String[] args) {

		SpringApplication.run(FindPatternApiApplication.class, args);
	}

	@Bean
	public Executor asyncExecutor() {
		ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
		executor.setCorePoolSize(4);
		executor.setMaxPoolSize(4);
		executor.setQueueCapacity(500);
		executor.setThreadNamePrefix("FindPattern");
		executor.initialize();
		return executor;
	}
}
