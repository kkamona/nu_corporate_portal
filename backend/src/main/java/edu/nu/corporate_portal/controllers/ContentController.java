package edu.nu.corporate_portal.controllers;

import edu.nu.corporate_portal.DTO.Content.ContentPostDTO;
import edu.nu.corporate_portal.DTO.Content.ContentGetDTO;
import edu.nu.corporate_portal.services.ContentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/contents")
public class ContentController {

    private final ContentService contentService;

    @Autowired
    public ContentController(ContentService contentService) {
        this.contentService = contentService;
    }

    @PostMapping(consumes = "multipart/form-data")
    public ResponseEntity<ContentGetDTO> createContent(
            @ModelAttribute ContentPostDTO dto,
            @RequestParam("mainPhoto") MultipartFile mainPhoto,
            @RequestParam(value = "attachments", required = false) List<MultipartFile> attachments,
            @RequestParam("userId") Long userId
    ) {
        ContentGetDTO created = contentService.createContent(dto, mainPhoto,
                attachments != null ? attachments : List.of(),
                userId);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ContentGetDTO> getContent(@PathVariable Long id) {
        ContentGetDTO dto = contentService.getContent(id);
        return ResponseEntity.ok(dto);
    }

    @GetMapping
    public ResponseEntity<List<ContentGetDTO>> listContents() {
        List<ContentGetDTO> list = contentService.listContents();
        return ResponseEntity.ok(list);
    }

    @PutMapping(value = "/{id}", consumes = "multipart/form-data")
    public ResponseEntity<ContentGetDTO> updateContent(
            @PathVariable Long id,
            @ModelAttribute ContentPostDTO dto,
            @RequestParam(value = "mainPhoto", required = false) MultipartFile mainPhoto,
            @RequestParam(value = "attachments", required = false) List<MultipartFile> attachments
    ) {
        ContentGetDTO updated = contentService.updateContent(
                id,
                dto,
                mainPhoto,
                attachments != null ? attachments : List.of()
        );
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteContent(@PathVariable Long id) {
        contentService.deleteContent(id);
        return ResponseEntity.noContent().build();
    }
}
