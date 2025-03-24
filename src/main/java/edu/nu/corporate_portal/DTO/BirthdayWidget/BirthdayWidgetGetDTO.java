package edu.nu.corporate_portal.DTO.BirthdayWidget;

import edu.nu.corporate_portal.models.BirthdayWidget;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class BirthdayWidgetGetDTO {
    private Long id;
    private Long userId;
    private LocalDate birthDate;
    private LocalDateTime createdAt;

    public BirthdayWidgetGetDTO(BirthdayWidget widget) {
        this.id = widget.getId();
        this.userId = widget.getUser().getId();
        this.birthDate = widget.getBirthDate();
        this.createdAt = widget.getCreatedAt();
    }
}

