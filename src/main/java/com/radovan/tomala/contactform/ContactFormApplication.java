package com.radovan.tomala.contactform;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
public class ContactFormApplication {

	public static void main(String[] args) {
		SpringApplication.run(ContactFormApplication.class, args);
	}

}
