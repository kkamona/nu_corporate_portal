package edu.nu.corporate_portal.DTO.Post;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Data
public class PostPostDTO {
    private String title;
    private String text;
    private String newThumbnail;
    private boolean isNews;
}
