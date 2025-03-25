package edu.nu.corporate_portal.DTO.Phonebook;

import edu.nu.corporate_portal.models.Phonebook;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class PhonebookGetDTO {
    private Long id;
    private Long userId;
    private String name;
    private String phoneNumber;
    private String email;
    private String department;
    private String type;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public PhonebookGetDTO(Phonebook phonebook) {
        this.id = phonebook.getId();
        this.userId = phonebook.getUser() != null ? phonebook.getUser().getId() : null;
        this.name = phonebook.getName();
        this.phoneNumber = phonebook.getPhoneNumber();
        this.email = phonebook.getEmail();
        this.department = phonebook.getDepartment();
        this.type = phonebook.getType();
        this.createdAt = phonebook.getCreatedAt();
        this.updatedAt = phonebook.getUpdatedAt();
    }
}