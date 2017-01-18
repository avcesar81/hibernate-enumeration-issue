package com.github.bilak;

import com.github.bilak.persistence.Initializer;
import com.github.bilak.persistence.jpa.repository.SimpleEntityRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class HibernateEnumerationIssueApplication {

	public static void main(String[] args) {
		SpringApplication.run(HibernateEnumerationIssueApplication.class, args);
	}

	@Bean
	Initializer initializer(SimpleEntityRepository repository) {
		return new Initializer(repository);
	}
}
