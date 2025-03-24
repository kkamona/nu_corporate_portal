package edu.nu.corporate_portal.DTO.BirthdayWidget;

import lombok.Data;

import java.time.LocalDate;

@Data
public class BirthdayWidgetPatchDTO {
    private Long userId;
    private LocalDate birthDate;
}

