package edu.nu.corporate_portal.DTO.Event;

import edu.nu.corporate_portal.models.Event;
import edu.nu.corporate_portal.models.Event.Role;
import edu.nu.corporate_portal.models.Event.School;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Set;

@Data
public class EventGetDTO {

    private Long id;
    private String title;
    private String description;
    private String location;
    private LocalDate startDate;
    private LocalDate endDate;
    private LocalTime startTime;
    private LocalTime endTime;
    private Boolean isPublic;
    private Set<Role> targetRoles;
    private Set<School> targetSchools;

    public EventGetDTO(Event e) {
        this.id = e.getId();
        this.title = e.getTitle();
        this.description = e.getDescription();
        this.location = e.getLocation();
        this.startDate = e.getStartDate();
        this.endDate = e.getEndDate();
        this.startTime = e.getStartTime();
        this.endTime = e.getEndTime();
        this.isPublic = e.isPublic();
        this.targetRoles = e.getTargetRoles();
        this.targetSchools = e.getTargetSchools();
    }
}
