package edu.nu.corporate_portal.models;

import edu.nu.corporate_portal.DTO.Event.EventPatchDTO;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Getter
@Entity
public class Event {

    @Id
    @GeneratedValue
    Long id;

    @Setter
    @Column(nullable = false)
    private String title;

    @Setter
    @Column(columnDefinition = "TEXT")
    private String description;

    @Setter
    private String location;

    @Setter
    @Column(name = "start_date", nullable = false)
    private LocalDate startDate;

    @Setter
    @Column(name = "end_date", nullable = false)
    private LocalDate endDate;

    @Setter
    @Column(name = "is_public", nullable = false)
    private boolean isPublic = true;

    @Setter
    @ElementCollection(targetClass = Role.class)
    @Enumerated(EnumType.STRING)
    @CollectionTable(name = "event_roles", joinColumns = @JoinColumn(name = "event_id"))
    @Column(name = "role")
    private Set<Role> targetRoles = new HashSet<>();

    @Setter
    @ElementCollection(targetClass = School.class)
    @Enumerated(EnumType.STRING)
    @CollectionTable(name = "event_schools", joinColumns = @JoinColumn(name = "event_id"))
    @Column(name = "school")
    private Set<School> targetSchools = new HashSet<>();

    public enum Role {
        STUDENT,  PROFESSOR, STAFF
    }

    public enum School {
        SEDS, SSH, SMG, NUSOM, CPS, GSB, GSPP, GSE
    }

    public void applyPatch(EventPatchDTO dto) {
        if (dto.getTitle() != null) {
            this.setTitle(dto.getTitle());
        }
        if (dto.getDescription() != null) {
            this.setDescription(dto.getDescription());
        }
        if (dto.getLocation() != null) {
            this.setLocation(dto.getLocation());
        }
        if (dto.getStartDate() != null) {
            this.setStartDate(dto.getStartDate());
        }
        if (dto.getEndDate() != null) {
            this.setEndDate(dto.getEndDate());
        }
        if (dto.getIsPublic() != null) {
            this.setPublic(dto.getIsPublic());
        }
        if (dto.getTargetRoles() != null) {
            this.setTargetRoles(dto.getTargetRoles());
        }
        if (dto.getTargetSchools() != null) {
            this.setTargetSchools(dto.getTargetSchools());
        }
    }
}
