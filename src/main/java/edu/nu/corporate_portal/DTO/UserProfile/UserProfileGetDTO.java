package edu.nu.corporate_portal.DTO.UserProfile;

import edu.nu.corporate_portal.models.UserProfile;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UserProfileGetDTO {
    private Long id;
    private Long userId;
    private String additionalInfo;
    private String phoneNumber;

    public UserProfileGetDTO(UserProfile profile) {
        this.id = profile.getId();
        this.userId = profile.getUser().getId();
        this.additionalInfo = profile.getAdditionalInfo();
        this.phoneNumber = profile.getPhoneNumber();
    }
}
