package com.lengoquyen0411.customer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication(scanBasePackages = {"com.lengoquyen0411.customer", "com.lengoquyen0411.library"})
@EnableJpaRepositories(value = "com.lengoquyen0411.library.Repo")
@EntityScan(value = "com.lengoquyen0411.library.Model")
public class CustomerApplication {

	public static void main(String[] args) {
		SpringApplication.run(CustomerApplication.class, args);
	}

}
