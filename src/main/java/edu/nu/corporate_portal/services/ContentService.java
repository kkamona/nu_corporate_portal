package edu.nu.corporate_portal.services;

import edu.nu.corporate_portal.models.Content;
import edu.nu.corporate_portal.models.ContentType;
import edu.nu.corporate_portal.repository.ContentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ContentService {

    private final ContentRepository contentRepository;

    @Autowired
    public ContentService(ContentRepository contentRepository) {
        this.contentRepository = contentRepository;
    }

    public List<Content> getAllContent() {
        return contentRepository.findAll();
    }

    public Content getContentById(Long id) {
        return contentRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Content not found"));
    }

    public List<Content> getContentByUserId(Long userId) {
        return contentRepository.findByUserId(userId);
    }

    public List<Content> getContentByType(ContentType type) {
        return contentRepository.findByType(type);
    }

    public Content createContent(Content content) {
        content.setCreatedAt(LocalDateTime.now());
        content.setUpdatedAt(LocalDateTime.now());
        return contentRepository.save(content);
    }

    public Content updateContent(Long id, Content updatedContent) {
        Content existingContent = getContentById(id);
        existingContent.setType(updatedContent.getType());
        existingContent.setFileUrl(updatedContent.getFileUrl());
        existingContent.setTitle(updatedContent.getTitle());
        existingContent.setContentText(updatedContent.getContentText());
        existingContent.setUpdatedAt(LocalDateTime.now());
        return contentRepository.save(existingContent);
    }

    public void deleteContent(Long id) {
        Content existingContent = getContentById(id);
        contentRepository.delete(existingContent);
    }
}
