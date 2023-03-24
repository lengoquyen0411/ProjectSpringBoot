package com.lengoquyen0411.admin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication(scanBasePackages = {"com.lengoquyen0411.library.*", "com.lengoquyen0411.admin.*"})
@EnableJpaRepositories(value = "com.lengoquyen0411.library.Repo")
@EntityScan(value = "com.lengoquyen0411.library.Model")
public class AdminApplication {

	public static void main(String[] args) {

		SpringApplication.run(AdminApplication.class, args);
	}

}
