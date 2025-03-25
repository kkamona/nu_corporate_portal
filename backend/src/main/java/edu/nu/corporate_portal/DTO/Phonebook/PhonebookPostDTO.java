package edu.nu.corporate_portal.DTO.Phonebook;

import lombok.Data;

@Data
public class PhonebookPostDTO {
    private Long userId;
    private String name;
    private String phoneNumber;
    private String email;
    private String department;
    private String type;
}
