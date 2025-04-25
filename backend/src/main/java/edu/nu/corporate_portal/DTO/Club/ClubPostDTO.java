package edu.nu.corporate_portal.DTO.Club;

import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
public class ClubPostDTO {
    private String name;
    private String description;
    private LocalDate foundationDate;
    private Boolean showMembers;
    private String location;
    private Long presidentId;
    private List<Long> memberIds;
}