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
//            String adminEmail = "admin@example.com";
//            String adminUsername = "admin";
//            User admin;
//            ObjectMapper objectMapper = new ObjectMapper();
//
//            if (!userService.emailExists(adminEmail)) {
//                admin = new User();
//                admin.setEmail(adminEmail);
//                admin.setUsername(adminUsername);
//                admin.setPasswordHash("admin123"); // TODO: Use a hashed password in production
//                admin.setRole(Role.admin);
//                admin.setDateOfBirth(LocalDate.of(2000, 1, 1));
//                admin = userService.createUser(admin);
//                System.out.println("✅ Admin user created: " + adminEmail);
//            } else {
//                admin = userService.getUserByEmail(adminEmail)
//                        .orElseThrow(() -> new RuntimeException("Admin user not found"));
//                System.out.println("⚠️ Admin user already exists.");
//            }
//
//            if (!userRightsService.userRightsExist(admin.getId())) {
//                UserRights adminRights = new UserRights(admin);
//                adminRights.setAssignedRole("admin");
//                adminRights.setCanEditCalendar(true);
//                adminRights.setCanManageUsers(true);
//                adminRights.setCanViewStaffInfo(true);
//                adminRights.setCanViewStudentInfo(true);
//                Map<String, Object> permissionsMap = objectMapper.readValue("{}", new TypeReference<Map<String, Object>>() {
//                });
//                adminRights.setPermissions(permissionsMap);
//                userRightsService.createUserRights(adminRights);
//                System.out.println("✅ Admin rights assigned.");
//            } else {
//                System.out.println("⚠️ Admin rights already exist.");
//            }

//            if (!userProfileService.userProfileExists(admin.getId())) {
//                UserProfile profile = new UserProfile();
//                profile.setUser(admin);
//                profile.setAdditionalInfo("Default admin profile");
//                profile.setPhoneNumber("000-000-0000");
//                userProfileService.createUserProfile(profile);
//                System.out.println("✅ Admin profile created.");
//            } else {
//                System.out.println("⚠️ Admin profile already exists.");
//            }
//
//            if (!phonebookService.userPhonebookExists(admin.getId())) {
//                Phonebook phonebook = new Phonebook();
//                phonebook.setUser(admin);
//                phonebook.setName("Admin Contact");
//                phonebook.setDepartment("Administration");
//                phonebook.setPhoneNumber("123-456-7890");
//                phonebook.setEmail(adminEmail);
//                phonebook.setType("emergency_contact");
//                phonebookService.createPhonebookEntry(phonebook);
//                System.out.println("✅ Admin phonebook entry created.");
//            } else {
//                System.out.println("⚠️ Admin phonebook entry already exists.");
//            }


//            List<NuCalendar> adminEvents = nuCalendarService.getEventsByCreatedBy(admin.getId());
//            if (adminEvents.isEmpty()) {
//                NuCalendar event = new NuCalendar();
//                event.setEventName("Default Admin Meeting");
//                event.setEventDate(LocalDate.now());
//                event.setStartTime(LocalDateTime.now());
//                event.setEndTime(LocalDateTime.now().plusHours(1));
//                event.setEventDescription("This is a default calendar event for the admin.");
//                event.setCreatedBy(admin.getId());
//                nuCalendarService.createEvent(event);
//                System.out.println("✅ Admin calendar event created.");
//            } else {
//                System.out.println("⚠️ Admin calendar event already exists.");
//            }

//            List<Content> adminContent = contentService.getContentByUserId(admin.getId());
//            if (adminContent.isEmpty()) {
//                Content content = new Content();
//                content.setUser(admin);
//                content.setType(ContentType.PDF);
//                content.setFileUrl("http://example.com/default.pdf");
//                content.setTitle("Default Admin Content");
//                content.setContentText("This is the default content for the admin.");
//                content.setCreatedAt(LocalDateTime.now());
//                content.setUpdatedAt(LocalDateTime.now());
//                contentService.createContent(content);
//                System.out.println("✅ Default admin content created.");
//            } else {
//                System.out.println("⚠️ Admin content already exists.");
//            }

//            if (!birthdayWidgetService.userBirthdayWidgetExists(admin.getId())) {
//                BirthdayWidget widget = new BirthdayWidget();
//                widget.setUser(admin);
//                widget.setBirthDate(admin.getDateOfBirth());
//                birthdayWidgetService.createWidget(widget);
//                System.out.println("✅ Admin birthday widget created.");
//            } else {
//                System.out.println("⚠️ Admin birthday widget already exists.");
//            }
        };
    }
}
