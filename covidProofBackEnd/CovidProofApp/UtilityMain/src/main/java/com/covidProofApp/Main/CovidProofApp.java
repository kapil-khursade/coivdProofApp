package com.covidProofApp.Main;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@ComponentScan(basePackages = {"com.covidProofApp.loginAndRegistration", "com.covidProodApp.Admin"})
@EntityScan(basePackages = {"com.covidProofApp.loginAndRegistration", "com.covidProodApp.Admin"})
@EnableJpaRepositories(basePackages = {"com.covidProofApp.loginAndRegistration", "com.covidProodApp.Admin"})
public class CovidProofApp {

	public static void main(String[] args) throws Exception {
		SpringApplication.run(CovidProofApp.class, args);
	}

}
