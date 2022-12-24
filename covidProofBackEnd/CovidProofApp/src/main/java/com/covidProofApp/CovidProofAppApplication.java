package com.covidProofApp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@ComponentScan(basePackages = {"com.covidProofApp.loginAndRegistration"})
@EntityScan(basePackages = {"com.covidProofApp.loginAndRegistration"})
@EnableJpaRepositories(basePackages = {"com.covidProofApp.loginAndRegistration"})
public class CovidProofAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(CovidProofAppApplication.class, args);
	}

}
