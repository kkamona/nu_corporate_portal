package edu.nu.corporate_portal.controllers;

import edu.nu.corporate_portal.DTO.Content.ContentGetDTO;
import edu.nu.corporate_portal.DTO.Content.ContentPatchDTO;
import edu.nu.corporate_portal.DTO.Content.ContentPostDTO;
import edu.nu.corporate_portal.models.Content;
import edu.nu.corporate_portal.models.ContentType;
import edu.nu.corporate_portal.services.ContentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/content")
public class ContentController {

    private final ContentService contentService;

    @Autowired
    public ContentController(ContentService contentService) {
        this.contentService = contentService;
    }

    @GetMapping
    public ResponseEntity<List<ContentGetDTO>> getAllContent() {
        List<ContentGetDTO> list = contentService.getAllContent()
                .stream()
                .map(ContentGetDTO::new)
                .toList();
        return ResponseEntity.ok(list);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ContentGetDTO> getContentById(@PathVariable Long id) {
        return ResponseEntity.ok(new ContentGetDTO(contentService.getContentById(id)));
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<ContentGetDTO>> getContentByUserId(@PathVariable Long userId) {
        List<ContentGetDTO> list = contentService.getContentByUserId(userId)
                .stream()
                .map(ContentGetDTO::new)
                .toList();
        return ResponseEntity.ok(list);
    }

    @GetMapping("/type/{dbValue}")
    public ResponseEntity<List<ContentGetDTO>> getContentByType(@PathVariable String dbValue) {
        try {
            ContentType type = ContentType.valueOf(dbValue.toUpperCase().replace(" ", "_"));
            List<ContentGetDTO> list = contentService.getContentByType(type)
                    .stream()
                    .map(ContentGetDTO::new)
                    .toList();
            return ResponseEntity.ok(list);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PostMapping
    public ResponseEntity<ContentGetDTO> createContent(@RequestBody ContentPostDTO dto) {
        Content created = contentService.createContent(dto);
        return ResponseEntity.ok(new ContentGetDTO(created));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<ContentGetDTO> updateContent(@PathVariable Long id, @RequestBody ContentPatchDTO dto) {
        Content updated = contentService.updateContent(id, dto);
        return ResponseEntity.ok(new ContentGetDTO(updated));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteContent(@PathVariable Long id) {
        contentService.deleteContent(id);
        return ResponseEntity.noContent().build();
    }
}

