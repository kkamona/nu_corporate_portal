package edu.nu.corporate_portal.controllers;

import edu.nu.corporate_portal.DTO.Post.PostGetDTO;
import edu.nu.corporate_portal.DTO.Post.PostPostDTO;
import edu.nu.corporate_portal.services.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/posts")
public class PostController {

    private final PostService postService;

    @Autowired
    public PostController(PostService postService) {
        this.postService = postService;
    }

    @PostMapping(consumes = "multipart/form-data")
    public ResponseEntity<PostGetDTO> createContent(
            @ModelAttribute PostPostDTO dto,
            @RequestParam("mainPhoto") MultipartFile mainPhoto,
            @RequestParam(value = "attachments", required = false) List<MultipartFile> attachments,
            @AuthenticationPrincipal Jwt jwt
    ) {
        Long userId = jwt.getClaim("id");
        PostGetDTO created = postService.createContent(
                dto,
                mainPhoto,
                attachments != null ? attachments : List.of(),
                userId
        );
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PostGetDTO> getContent(@PathVariable Long id) {
        PostGetDTO dto = postService.getContent(id);
        return ResponseEntity.ok(dto);
    }

    @GetMapping
    public ResponseEntity<Page<PostGetDTO>> listContents(
            @PageableDefault(size = 10, page = 0) Pageable pageable
    ) {
        Page<PostGetDTO> page = postService.listContents(pageable);
        return ResponseEntity.ok(page);
    }

    @PutMapping(value = "/{id}", consumes = "multipart/form-data")
    public ResponseEntity<PostGetDTO> updateContent(
            @PathVariable Long id,
            @ModelAttribute PostPostDTO dto,
            @RequestParam(value = "attachments", required = false) List<MultipartFile> attachments
    ) {
        PostGetDTO updated = postService.updateContent(
                id,
                dto,
                attachments != null ? attachments : List.of()
        );
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteContent(@PathVariable Long id) {
        postService.deleteContent(id);
        return ResponseEntity.noContent().build();
    }
}
