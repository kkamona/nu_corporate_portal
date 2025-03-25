package edu.nu.corporate_portal.DTO.News;

import lombok.Data;

@Data
public class NewsPostDTO {
    private Long userId;
    private String title;
    private String content;
}

