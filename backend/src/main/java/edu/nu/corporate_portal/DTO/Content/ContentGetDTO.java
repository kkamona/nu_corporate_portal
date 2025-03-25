package edu.nu.corporate_portal.DTO.Content;

import edu.nu.corporate_portal.models.Content;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class ContentGetDTO {
    private Long id;
    private Long userId;
    private String type;
    private String fileUrl;
    private String title;
    private String contentText;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public ContentGetDTO(Content content) {
        this.id = content.getId();
        this.userId = content.getUser().getId();
        this.type = content.getType().name();
        this.fileUrl = content.getFileUrl();
        this.title = content.getTitle();
        this.contentText = content.getContentText();
        this.createdAt = content.getCreatedAt();
        this.updatedAt = content.getUpdatedAt();
    }
}