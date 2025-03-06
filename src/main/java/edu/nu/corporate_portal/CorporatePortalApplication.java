package edu.nu.corporate_portal;

import edu.nu.corporate_portal.models.BirthdayWidget;
import edu.nu.corporate_portal.models.Content;
import edu.nu.corporate_portal.models.ContentType;
import edu.nu.corporate_portal.models.NuCalendar;
import edu.nu.corporate_portal.models.Phonebook;
import edu.nu.corporate_portal.models.User;
import edu.nu.corporate_portal.models.User.Role;
import edu.nu.corporate_portal.models.UserRights;
import edu.nu.corporate_portal.models.UserProfile;
import edu.nu.corporate_portal.services.BirthdayWidgetService;
import edu.nu.corporate_portal.services.ContentService;
import edu.nu.corporate_portal.services.NuCalendarService;
import edu.nu.corporate_portal.services.PhonebookService;
import edu.nu.corporate_portal.services.UserProfileService;
import edu.nu.corporate_portal.services.UserRightsService;
import edu.nu.corporate_portal.services.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

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
			String adminEmail = "admin@example.com";
			String adminUsername = "admin";
			User admin;
			ObjectMapper objectMapper = new ObjectMapper();

			// Create admin user if not exists
			if (!userService.emailExists(adminEmail)) {
				admin = new User();
				admin.setEmail(adminEmail);
				admin.setUsername(adminUsername);
				admin.setPasswordHash("admin123"); // TODO: Use a hashed password in production
				admin.setRole(Role.admin);          // Ensure your Role enum contains "admin" in lowercase
				admin.setDateOfBirth(LocalDate.of(2000, 1, 1));
				admin = userService.createUser(admin);
				System.out.println("✅ Admin user created: " + adminEmail);
			} else {
				admin = userService.getUserByEmail(adminEmail)
						.orElseThrow(() -> new RuntimeException("Admin user not found"));
				System.out.println("⚠️ Admin user already exists.");
			}

			// Create admin rights if not exists
			if (!userRightsService.userRightsExist(admin.getId())) {
				UserRights adminRights = new UserRights(admin);
				adminRights.setAssignedRole("admin");
				adminRights.setCanEditCalendar(true);
				adminRights.setCanManageUsers(true);
				adminRights.setCanViewStaffInfo(true);
				adminRights.setCanViewStudentInfo(true);
				// Convert empty JSON object string to Map<String, Object>
				Map<String, Object> permissionsMap = objectMapper.readValue("{}", new TypeReference<Map<String, Object>>() {});
				adminRights.setPermissions(permissionsMap);
				userRightsService.createUserRights(adminRights);
				System.out.println("✅ Admin rights assigned.");
			} else {
				System.out.println("⚠️ Admin rights already exist.");
			}

			// Create admin profile if not exists
			if (!userProfileService.userProfileExists(admin.getId())) {
				UserProfile profile = new UserProfile();
				profile.setUser(admin);
				profile.setAdditionalInfo("Default admin profile");
				profile.setPhoneNumber("000-000-0000");
				userProfileService.createUserProfile(profile);
				System.out.println("✅ Admin profile created.");
			} else {
				System.out.println("⚠️ Admin profile already exists.");
			}

			// Create phonebook entry for admin if not exists
			if (!phonebookService.userPhonebookExists(admin.getId())) {
				Phonebook phonebook = new Phonebook();
				phonebook.setUser(admin);
				phonebook.setName("Admin Contact"); // Required field
				phonebook.setDepartment("Administration");
				phonebook.setPhoneNumber("123-456-7890");
				phonebook.setEmail(adminEmail);
				phonebook.setType("emergency_contact"); // Allowed values: 'emergency_contact', 'service_provider', 'reference'
				phonebookService.createPhonebookEntry(phonebook);
				System.out.println("✅ Admin phonebook entry created.");
			} else {
				System.out.println("⚠️ Admin phonebook entry already exists.");
			}

			// Create a default calendar event for admin if not exists
			List<NuCalendar> adminEvents = nuCalendarService.getEventsByCreatedBy(admin.getId());
			if (adminEvents.isEmpty()) {
				NuCalendar event = new NuCalendar();
				event.setEventName("Default Admin Meeting");
				event.setEventDate(LocalDate.now());
				event.setStartTime(LocalDateTime.now());
				event.setEndTime(LocalDateTime.now().plusHours(1));
				event.setEventDescription("This is a default calendar event for the admin.");
				event.setCreatedBy(admin.getId());
				nuCalendarService.createEvent(event);
				System.out.println("✅ Admin calendar event created.");
			} else {
				System.out.println("⚠️ Admin calendar event already exists.");
			}

			// Create default content for admin if not exists
			List<Content> adminContent = contentService.getContentByUserId(admin.getId());
			if (adminContent.isEmpty()) {
				Content content = new Content();
				content.setUser(admin);
				content.setType(ContentType.PDF); // Example: PDF
				content.setFileUrl("http://example.com/default.pdf");
				content.setTitle("Default Admin Content");
				content.setContentText("This is the default content for the admin.");
				content.setCreatedAt(LocalDateTime.now());
				content.setUpdatedAt(LocalDateTime.now());
				contentService.createContent(content);
				System.out.println("✅ Default admin content created.");
			} else {
				System.out.println("⚠️ Admin content already exists.");
			}

			// Create a default birthday widget for admin if not exists
			if (!birthdayWidgetService.userBirthdayWidgetExists(admin.getId())) {
				BirthdayWidget widget = new BirthdayWidget();
				widget.setUser(admin);
				widget.setBirthDate(admin.getDateOfBirth());
				birthdayWidgetService.createWidget(widget);
				System.out.println("✅ Admin birthday widget created.");
			} else {
				System.out.println("⚠️ Admin birthday widget already exists.");
			}
		};
	}
}
