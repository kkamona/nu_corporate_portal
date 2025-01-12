package edu.nu.corporate_portal;


import edu.nu.corporate_portal.TestEntity;
import edu.nu.corporate_portal.TestRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


	@SpringBootApplication
	public class CorporatePortalApplication {

		public static void main(String[] args) {
			SpringApplication.run(CorporatePortalApplication.class, args);
		}

		@Bean
		CommandLineRunner run(TestRepository repository) {
			return args -> {
				TestEntity entity = new TestEntity();
				entity.setName("Hello, Spring Boot!"); // Set the "name" field.
				repository.save(entity); // Save to the database.
				System.out.println("Data saved to database!");
			};
		}
	}

