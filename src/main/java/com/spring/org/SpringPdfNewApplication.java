package com.spring.org;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication(scanBasePackages = "com")
@EnableJpaRepositories(basePackages = "com.spring.repository")
@EntityScan(basePackages = "com.spring")
public class SpringPdfNewApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringPdfNewApplication.class, args);
	}

}
