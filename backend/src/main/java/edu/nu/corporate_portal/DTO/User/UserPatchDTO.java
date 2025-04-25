package edu.nu.corporate_portal.DTO.User;

import com.nimbusds.openid.connect.sdk.claims.Gender;
import edu.nu.corporate_portal.models.User;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Setter
@Getter
@Data
public class UserPatchDTO {
    private String email;
    private String passwordHash;
    private String firstName;
    private String lastName;
    private String contactInfo;
    private LocalDate dateOfBirth;
    private User.School school;
    private String major;
    private User.Role role;

    private Boolean showName;
    private Boolean showContactInfo;
    private Boolean showDateOfBirth;
    private Boolean showSchool;
    private Boolean showMajor;
    private Boolean showProfilePicture;
    private Gender gender;
    private String interests;
}
