package edu.nu.corporate_portal.DTO.Content;

import edu.nu.corporate_portal.models.ContentType;
import lombok.Data;

@Data
public class ContentPostDTO {
    private Long userId;
    private ContentType type;
    private String fileUrl;
    private String title;
    private String contentText;
}

