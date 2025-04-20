package edu.nu.corporate_portal.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
@Entity
@Table(name = "content")
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @CreationTimestamp
    @Column(name = "created_at", nullable = false, updatable = false)
    private Instant createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at", nullable = false)
    private Instant updatedAt;

    private String title;

    @Column(columnDefinition = "TEXT")
    private String text;

    @Column(name = "main_photo_url")
    private String mainPhotoUrl;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ElementCollection(fetch = FetchType.LAZY)
    @CollectionTable(
            name = "content_attachments",
            joinColumns = @JoinColumn(name = "content_id")
    )
    @Column(name = "attachment_url")
    private List<String> attachments = new ArrayList<>();

}
