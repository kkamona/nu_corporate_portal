package edu.nu.corporate_portal.DTO.User;

import com.nimbusds.openid.connect.sdk.claims.Gender;
import edu.nu.corporate_portal.DTO.Club.ClubSummaryDTO;
import edu.nu.corporate_portal.models.User;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
public class UserGetDTO {
    private Long id;
    private String email;
    private String firstName;
    private String lastName;
    private String contactInfo;
    private String profilePicture;
    private LocalDate dateOfBirth;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private String school;
    private String major;
    private String role;
  
    private boolean showName;
    private boolean showContactInfo;
    private boolean showDateOfBirth;
    private boolean showSchool;
    private boolean showMajor;
    private boolean showProfilePicture;
    private String gender;
    private String interests;
    private List<ClubSummaryDTO> clubs;

    public UserGetDTO(User user) {
        this.id = user.getId();
        this.email = user.getEmail();
        this.firstName = user.getFirstName();
        this.lastName = user.getLastName();
        this.contactInfo = user.getContactInfo();
        this.profilePicture = user.getProfilePicture();
        this.dateOfBirth = user.getDateOfBirth();
        this.createdAt = user.getCreatedAt();
        this.updatedAt = user.getUpdatedAt();
        this.school = user.getSchool() != null ? user.getSchool().name() : null;
        this.major = user.getMajor();
        this.role = user.getRole() != null ? user.getRole().name() : null;
        this.interests = user.getInterests();
        this.gender = user.getGender();
        this.clubs = user.getClubs().stream()
                .map(c -> new ClubSummaryDTO(c.getId(), c.getName()))
                .toList();

        this.showName = user.isShowName();
        this.showContactInfo = user.isShowContactInfo();
        this.showDateOfBirth = user.isShowDateOfBirth();
        this.showSchool = user.isShowSchool();
        this.showMajor = user.isShowMajor();
        this.showProfilePicture = user.isShowProfilePicture();
    }
}
