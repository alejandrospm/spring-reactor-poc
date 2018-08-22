package com.reactor.event.bus.poc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import reactor.core.publisher.ReplayProcessor;

@SpringBootApplication
public class Application {

	@Bean
	public ReplayProcessor getReplayProcessor(){

		return ReplayProcessor.create();

	}

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}
}
