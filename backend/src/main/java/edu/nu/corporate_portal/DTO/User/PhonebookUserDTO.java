package edu.nu.corporate_portal.DTO.User;

import edu.nu.corporate_portal.models.User;
import lombok.Data;

@Data
public class PhonebookUserDTO {
    private Long id;
    private String firstName;
    private String lastName;
    private String contactInfo;
    private User.Role role;

    public PhonebookUserDTO(Long id, String firstName, String lastName, String contactInfo, User.Role role) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.contactInfo = contactInfo;
        this.role = role;
    }
}