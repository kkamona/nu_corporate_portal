package edu.nu.corporate_portal.DTO.UserProfile;

import lombok.Data;

@Data
public class UserProfilePostDTO {
    private Long userId;
    private String additionalInfo;
    private String phoneNumber;
}
