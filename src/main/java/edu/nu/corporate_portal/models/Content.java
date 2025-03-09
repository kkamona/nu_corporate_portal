package edu.nu.corporate_portal.models;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "content")
public class Content {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "content_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Convert(converter = ContentTypeConverter.class)
    @Column(name = "type", nullable = false)
    private ContentType type;

    @Column(name = "file_url", length = 255)
    private String fileUrl;

    @Column(name = "title", length = 255)
    private String title;

    @Column(name = "content_text", columnDefinition = "text")
    private String contentText;

    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt = LocalDateTime.now();

    @Column(name = "updated_at", nullable = false)
    private LocalDateTime updatedAt = LocalDateTime.now();

    public Content() {}

    public Content(User user, ContentType type, String fileUrl, String title, String contentText) {
        this.user = user;
        this.type = type;
        this.fileUrl = fileUrl;
        this.title = title;
        this.contentText = contentText;
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
    }

    // Getters and Setters

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }
    public void setUser(User user) {
        this.user = user;
    }

    public ContentType getType() {
        return type;
    }
    public void setType(ContentType type) {
        this.type = type;
    }

    public String getFileUrl() {
        return fileUrl;
    }
    public void setFileUrl(String fileUrl) {
        this.fileUrl = fileUrl;
    }

    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }

    public String getContentText() {
        return contentText;
    }
    public void setContentText(String contentText) {
        this.contentText = contentText;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }
    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }
    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }
}
