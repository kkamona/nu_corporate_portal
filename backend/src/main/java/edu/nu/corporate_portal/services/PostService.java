package edu.nu.corporate_portal.services;

import edu.nu.corporate_portal.DTO.Post.PostGetDTO;
import edu.nu.corporate_portal.DTO.Post.PostPostDTO;
import edu.nu.corporate_portal.models.Post;
import edu.nu.corporate_portal.models.User;
import edu.nu.corporate_portal.repository.PostRepository;
import edu.nu.corporate_portal.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.stream.Collectors;

@Service("contentService")
public class PostService {

    private final PostRepository postRepo;
    private final UserRepository userRepo;
    private final AzureBlobStorageService storage;

    @Autowired
    public PostService(
            PostRepository postRepo,
            UserRepository userRepo,
            AzureBlobStorageService storage
    ) {
        this.postRepo = postRepo;
        this.userRepo = userRepo;
        this.storage = storage;
    }

    public PostGetDTO createContent(
            PostPostDTO dto,
            List<MultipartFile> attachments,
            MultipartFile newsThumbnail,
            Long userId
    ) {
        System.out.println(dto);
        User user = userRepo.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("User not found: " + userId));

        List<String> attachUrls = attachments.stream()
                .filter(f -> !f.isEmpty())
                .map(storage::uploadFile)
                .collect(Collectors.toList());


        String thumbnailUrl = null;
        if (newsThumbnail != null && !newsThumbnail.isEmpty()) {
            thumbnailUrl = storage.uploadFile(newsThumbnail);
        }

        Post post = new Post();
        post.setTitle(dto.getTitle());
        post.setText(dto.getText());
        post.setNews(dto.isNews());
        post.setAttachments(attachUrls);
        post.setNewsThumbnail(thumbnailUrl);
        post.setUser(user);

        Post saved = postRepo.save(post);
        return mapToGetDTO(saved);
    }

    public PostGetDTO getContent(Long id) {
        return mapToGetDTO(findById(id));
    }

    public Page<PostGetDTO> listContents(Pageable pageable) {
        return postRepo.findAll(pageable)
                .map(this::mapToGetDTO);
    }

    public PostGetDTO updateContent(
            Long id,
            PostPostDTO dto,
            List<MultipartFile> attachments
    ) {
        Post post = findById(id);

        if (dto.getTitle() != null) {
            post.setTitle(dto.getTitle());
        }
        if (dto.getText() != null) {
            post.setText(dto.getText());
        }
        if (attachments != null && !attachments.isEmpty()) {
            List<String> newUrls = attachments.stream()
                    .filter(f -> !f.isEmpty())
                    .map(storage::uploadFile)
                    .collect(Collectors.toList());
            post.setAttachments(newUrls);
        }

        Post updated = postRepo.save(post);
        return mapToGetDTO(updated);
    }

    public void deleteContent(Long id) {
        findById(id);
        postRepo.deleteById(id);
    }

    private Post findById(Long id) {
        return postRepo.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Content not found: " + id));
    }

    private PostGetDTO mapToGetDTO(Post c) {
        return new PostGetDTO(
                c.getId(),
                c.getUser().getId(),
                c.getTitle(),
                c.getText(),
                c.getAttachments(),
                c.isNews(),
                c.getNewsThumbnail()
        );
    }
}
