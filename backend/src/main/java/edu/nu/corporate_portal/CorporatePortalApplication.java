package edu.nu.corporate_portal;

import edu.nu.corporate_portal.services.*;
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
    public CommandLineRunner initializeAdmin(UserService userService,
                                             UserRightsService userRightsService,
                                             UserProfileService userProfileService,
                                             PhonebookService phonebookService,
                                             NuCalendarService nuCalendarService,
                                             ContentService contentService,
                                             BirthdayWidgetService birthdayWidgetService) {
        return args -> {

        };
    }
}
