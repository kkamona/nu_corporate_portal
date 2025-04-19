package edu.nu.corporate_portal.DTO.Event;

import edu.nu.corporate_portal.models.Event.Role;
import edu.nu.corporate_portal.models.Event.School;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Set;

@Data
public class EventPostDTO {

    @NotBlank
    private String title;

    private String description;
    private String location;

    @NotNull
    @FutureOrPresent
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDate startDate;

    @NotNull
    @FutureOrPresent
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDate endDate;

    @NotNull
    @DateTimeFormat(iso = DateTimeFormat.ISO.TIME)
    private LocalTime startTime;

    @NotNull
    @DateTimeFormat(iso = DateTimeFormat.ISO.TIME)
    private LocalTime endTime;

    @NotNull
    private Boolean isPublic = Boolean.TRUE;

    private Set<Role> targetRoles;
    private Set<School> targetSchools;
}
