package com.udacity.course3.reviews;

import org.flywaydb.core.Flyway;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class ReviewsApplication {

	public static void main(String[] args) {
		SpringApplication.run(ReviewsApplication.class, args);
	}

	@Bean
	CommandLineRunner initDatabase(
			@Value("${spring.datasource.url}") String url,
			@Value("${spring.datasource.username}") String username,
			@Value("${spring.datasource.password}") String password
	) {
		return args -> {
			if(!url.equals(null) && !url.equals("")) {
				Flyway flyway = Flyway.configure().dataSource(url, username, password).load();
				flyway.migrate();
			}
		};
	}

}