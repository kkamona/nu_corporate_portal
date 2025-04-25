package edu.nu.corporate_portal.DTO.Event;

import edu.nu.corporate_portal.models.Event.Role;
import edu.nu.corporate_portal.models.Event.School;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.util.Set;

@Data
public class EventPatchDTO {

    private String title;
    private String description;
    private String location;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDate startDate;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDate endDate;

    @NotNull
    @Pattern(
            regexp = "^([01]\\d|2[0-3]):[0-5]\\d$",
            message = "startTime must be in 24‑hour HH:mm format"
    )
    private String startTime;

    @NotNull
    @Pattern(
            regexp = "^([01]\\d|2[0-3]):[0-5]\\d$",
            message = "endTime must be in 24‑hour HH:mm format"
    )
    private String endTime;


    private Boolean isPublic;
    private Set<Role> targetRoles;
    private Set<School> targetSchools;
}
