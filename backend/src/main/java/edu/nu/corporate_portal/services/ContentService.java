package edu.nu.corporate_portal.services;

import edu.nu.corporate_portal.DTO.Content.ContentPatchDTO;
import edu.nu.corporate_portal.DTO.Content.ContentPostDTO;
import edu.nu.corporate_portal.models.Content;
import edu.nu.corporate_portal.models.ContentType;
import edu.nu.corporate_portal.models.User;
import edu.nu.corporate_portal.repository.ContentRepository;
import edu.nu.corporate_portal.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ContentService {

    private final ContentRepository contentRepository;
    private final UserRepository userRepository;

    @Autowired
    public ContentService(ContentRepository contentRepository, UserRepository userRepository) {
        this.contentRepository = contentRepository;
        this.userRepository = userRepository;
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

    public Content createContent(ContentPostDTO dto) {
        User user = userRepository.findById(dto.getUserId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found"));

        Content content = new Content();
        content.setUser(user);
        content.setType(dto.getType());
        content.setFileUrl(dto.getFileUrl());
        content.setTitle(dto.getTitle());
        content.setContentText(dto.getContentText());
        content.setCreatedAt(LocalDateTime.now());
        content.setUpdatedAt(LocalDateTime.now());

        return contentRepository.save(content);
    }

    public Content updateContent(Long id, ContentPatchDTO dto) {
        Content content = getContentById(id);

        if (dto.getType() != null) {
            content.setType(dto.getType());
        }
        if (dto.getFileUrl() != null) content.setFileUrl(dto.getFileUrl());
        if (dto.getTitle() != null) content.setTitle(dto.getTitle());
        if (dto.getContentText() != null) content.setContentText(dto.getContentText());

        content.setUpdatedAt(LocalDateTime.now());
        return contentRepository.save(content);
    }

    public void deleteContent(Long id) {
        Content content = getContentById(id);
        contentRepository.delete(content);
    }
}
