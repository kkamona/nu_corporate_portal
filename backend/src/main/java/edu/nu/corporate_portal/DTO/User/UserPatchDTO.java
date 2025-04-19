package edu.nu.corporate_portal.DTO.User;

import edu.nu.corporate_portal.models.User;
import lombok.Data;

import java.time.LocalDate;

@Data
public class UserPatchDTO {
    private String email;
    private String passwordHash;
    private String firstName;
    private String lastName;
    private String contactInfo;
    private String profilePictureBase64;
    private LocalDate dateOfBirth;
    private User.School school;
    private String major;
    private User.Role role;

}
