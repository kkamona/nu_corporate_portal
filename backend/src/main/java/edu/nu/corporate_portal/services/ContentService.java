package edu.nu.corporate_portal.services;

import edu.nu.corporate_portal.DTO.Content.ContentPostDTO;
import edu.nu.corporate_portal.DTO.Content.ContentGetDTO;
import edu.nu.corporate_portal.models.Content;
import edu.nu.corporate_portal.models.User;
import edu.nu.corporate_portal.repository.ContentRepository;
import edu.nu.corporate_portal.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.stream.Collectors;

@Service("contentService")
public class ContentService {

    private final ContentRepository contentRepo;
    private final UserRepository userRepo;
    private final AzureBlobStorageService storage;

    @Autowired
    public ContentService(
            ContentRepository contentRepo,
            UserRepository userRepo,
            AzureBlobStorageService storage
    ) {
        this.contentRepo = contentRepo;
        this.userRepo = userRepo;
        this.storage = storage;
    }

    public ContentGetDTO createContent(
            ContentPostDTO dto,
            MultipartFile mainPhoto,
            List<MultipartFile> attachments,
            Long userId
    ) {
        User user = userRepo.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("User not found: " + userId));

        String mainUrl = storage.uploadFile(mainPhoto);
        List<String> attachUrls = attachments.stream()
                .filter(f -> !f.isEmpty())
                .map(storage::uploadFile)
                .collect(Collectors.toList());

        Content content = new Content();
        content.setTitle(dto.getTitle());
        content.setText(dto.getText());
        content.setMainPhotoUrl(mainUrl);
        content.setAttachments(attachUrls);
        content.setUser(user);

        Content saved = contentRepo.save(content);
        return mapToGetDTO(saved);
    }

    public ContentGetDTO getContent(Long id) {
        return mapToGetDTO(findById(id));
    }

    public List<ContentGetDTO> listContents() {
        return contentRepo.findAll()
                .stream()
                .map(this::mapToGetDTO)
                .collect(Collectors.toList());
    }

    public ContentGetDTO updateContent(
            Long id,
            ContentPostDTO dto,
            MultipartFile mainPhoto,
            List<MultipartFile> attachments
    ) {
        Content content = findById(id);

        if (dto.getTitle() != null) {
            content.setTitle(dto.getTitle());
        }
        if (dto.getText() != null) {
            content.setText(dto.getText());
        }
        if (mainPhoto != null && !mainPhoto.isEmpty()) {
            content.setMainPhotoUrl(storage.uploadFile(mainPhoto));
        }
        if (attachments != null && !attachments.isEmpty()) {
            List<String> newUrls = attachments.stream()
                    .filter(f -> !f.isEmpty())
                    .map(storage::uploadFile)
                    .collect(Collectors.toList());
            content.setAttachments(newUrls);
        }

        Content updated = contentRepo.save(content);
        return mapToGetDTO(updated);
    }

    public void deleteContent(Long id) {
        findById(id);
        contentRepo.deleteById(id);
    }


    private Content findById(Long id) {
        return contentRepo.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Content not found: " + id));
    }

    private ContentGetDTO mapToGetDTO(Content c) {
        return new ContentGetDTO(
                c.getId(),
                c.getUser().getId(),
                c.getTitle(),
                c.getText(),
                c.getMainPhotoUrl(),
                c.getAttachments()
        );
    }
}
