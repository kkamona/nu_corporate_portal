package edu.nu.corporate_portal.DTO.News;

import edu.nu.corporate_portal.models.News;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class NewsGetDTO {
    private Long id;
    private Long userId;
    private String title;
    private String content;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private LocalDate publishedDate;

    public NewsGetDTO(News news) {
        this.id = news.getId();
        this.userId = news.getUser().getId();
        this.title = news.getTitle();
        this.content = news.getContent();
        this.createdAt = news.getCreatedAt();
        this.updatedAt = news.getUpdatedAt();
        this.publishedDate = news.getPublishedDate();
    }
}
