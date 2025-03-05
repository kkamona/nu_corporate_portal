package edu.nu.corporate_portal;
import java.time.LocalDate;

import edu.nu.corporate_portal.models.User;
import edu.nu.corporate_portal.models.User.Role;
import edu.nu.corporate_portal.services.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class CorporatePortalApplication {

	public static void main(String[] args) {
		SpringApplication.run(CorporatePortalApplication.class, args);
	}

	@Bean
	public CommandLineRunner createAdminUser(UserService userService) {
		return args -> {
			String adminEmail = "admin@example.com";
			String adminUsername = "admin";

			if (!userService.emailExists(adminEmail)) {
				User admin = new User();
				admin.setEmail(adminEmail);
				admin.setUsername(adminUsername);
				admin.setPasswordHash("admin123"); // TODO: Hash this before production
				admin.setRole(Role.admin);
				admin.setDateOfBirth(LocalDate.of(2000, 1, 1));

				userService.createUser(admin);
				System.out.println("✅ Admin user created: " + adminEmail);
			} else {
				System.out.println("⚠️ Admin user already exists.");
			}
		};
	}
}
