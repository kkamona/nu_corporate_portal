package edu.nu.corporate_portal.DTO.Post;

import lombok.Getter;

import java.util.List;

@Getter
public class PostGetDTO {
    private Long id;
    private Long userId;
    private String title;
    private String text;
    private String newsThumbnail;
    private boolean isNews;
    private List<String> attachments;

    public PostGetDTO(Long id, Long userId, String title, String text,
                       List<String> attachments, boolean isNews, String newsThumbnail) {
        this.id = id;
        this.userId = userId;
        this.title = title;
        this.text = text;
        this.attachments = attachments;
        this.isNews = isNews;
        this.newsThumbnail = newsThumbnail;
    }

}
