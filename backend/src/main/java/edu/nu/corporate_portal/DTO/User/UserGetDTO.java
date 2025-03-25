package edu.nu.corporate_portal.DTO.User;

import edu.nu.corporate_portal.models.User;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class UserGetDTO {
    private Long id;
    private String email;
    private String username;
    private String firstName;
    private String lastName;
    private String contactInfo;
    private String profilePicture;
    private LocalDate dateOfBirth;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private String azureSsoId;
    private String authenticationProvider;
    private Integer year;
    private String school;
    private String major;
    private String department;
    private String role;

    public UserGetDTO(User user) {
        this.id = user.getId();
        this.email = user.getEmail();
        this.username = user.getUsername();
        this.firstName = user.getFirstName();
        this.lastName = user.getLastName();
        this.contactInfo = user.getContactInfo();
        this.profilePicture = user.getProfilePicture();
        this.dateOfBirth = user.getDateOfBirth();
        this.createdAt = user.getCreatedAt();
        this.updatedAt = user.getUpdatedAt();
        this.azureSsoId = user.getAzureSsoId();
        this.authenticationProvider = user.getAuthenticationProvider().name();
        this.year = user.getYear();
        this.school = user.getSchool() != null ? user.getSchool().name() : null;
        this.major = user.getMajor();
        this.department = user.getDepartment();
        this.role = user.getRole().name();
    }
}
