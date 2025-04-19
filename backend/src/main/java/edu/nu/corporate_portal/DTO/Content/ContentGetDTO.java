package edu.nu.corporate_portal.DTO.Content;

import lombok.Getter;

import java.util.List;

@Getter
public class ContentGetDTO {
    private Long id;
    private Long userId;
    private String title;
    private String text;
    private String mainPhotoUrl;
    private List<String> attachments;

    public ContentGetDTO(Long id, Long userId, String title, String text,
                         String mainPhotoUrl, List<String> attachments) {
        this.id = id;
        this.title = title;
        this.text = text;
        this.mainPhotoUrl = mainPhotoUrl;
        this.attachments = attachments;
    }

}
