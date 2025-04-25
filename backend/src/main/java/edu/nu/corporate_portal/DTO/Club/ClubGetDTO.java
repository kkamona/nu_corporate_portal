package edu.nu.corporate_portal.DTO.Club;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import edu.nu.corporate_portal.models.Club;

@Data
@AllArgsConstructor
public class ClubGetDTO {
    private Long id;
    private String name;
    private String description;
    private LocalDate foundationDate;
    private boolean showMembers;
    private String location;
    private Long presidentId;
    private List<Long> memberIds;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private String profilePicture;

    public ClubGetDTO(Club club) {
        this.id = club.getId();
        this.name = club.getName();
        this.description = club.getDescription();
        this.foundationDate = club.getFoundationDate();
        this.showMembers = club.isShowMembers();
        this.location = club.getLocation();
        this.presidentId = club.getPresident().getId();
        this.memberIds = club.getMembers().stream()
                .map(User -> User.getId())
                .toList();
        this.createdAt = club.getCreatedAt();
        this.updatedAt = club.getUpdatedAt();
        this.profilePicture = club.getProfilePicture();
    }
}
