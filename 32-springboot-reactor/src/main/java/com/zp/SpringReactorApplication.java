package com.zp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories
public class SpringReactorApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringReactorApplication.class, args);
	}
}
