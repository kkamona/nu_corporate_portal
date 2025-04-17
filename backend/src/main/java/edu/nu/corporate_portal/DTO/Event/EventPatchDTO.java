package edu.nu.corporate_portal.DTO.Event;

import edu.nu.corporate_portal.models.Event.Role;
import edu.nu.corporate_portal.models.Event.School;
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

    private Boolean isPublic;
    private Set<Role> targetRoles;
    private Set<School> targetSchools;
}
