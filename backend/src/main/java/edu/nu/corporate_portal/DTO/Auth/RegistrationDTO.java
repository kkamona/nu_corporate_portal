package edu.nu.corporate_portal.DTO.Auth;

import edu.nu.corporate_portal.models.User;
import lombok.Data;

import java.time.LocalDate;

@Data
public class RegistrationDTO {
    private String email;
    private String password;
    private String firstName;
    private String lastName;
    private String contactInfo;
    private LocalDate dateOfBirth;
    private User.School school;
    private String major;
    private User.Role role;
    private String azureSsoId;
    private String profilePicture;
}
