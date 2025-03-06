package edu.nu.corporate_portal.controllers;

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
    public ResponseEntity<List<Content>> getAllContent() {
        return ResponseEntity.ok(contentService.getAllContent());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Content> getContentById(@PathVariable Long id) {
        return ResponseEntity.ok(contentService.getContentById(id));
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Content>> getContentByUserId(@PathVariable Long userId) {
        return ResponseEntity.ok(contentService.getContentByUserId(userId));
    }

    @GetMapping("/type/{dbValue}")
    public ResponseEntity<List<Content>> getContentByType(@PathVariable String dbValue) {
        // Convert string to enum using the converter logic (or do it manually)
        // If your DB uses EXACT strings: 'video', 'photo', 'formatted text', etc.
        // You can do a small switch or a custom method:
        ContentType type;
        switch (dbValue) {
            case "video":            type = ContentType.VIDEO;           break;
            case "photo":            type = ContentType.PHOTO;           break;
            case "formatted text":   type = ContentType.FORMATTED_TEXT;  break;
            case "table":            type = ContentType.TABLE;           break;
            case "docx":             type = ContentType.DOCX;            break;
            case "xlsx":             type = ContentType.XLSX;            break;
            case "pptx":             type = ContentType.PPTX;            break;
            case "pdf":              type = ContentType.PDF;             break;
            default:
                return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(contentService.getContentByType(type));
    }

    @PostMapping
    public ResponseEntity<Content> createContent(@RequestBody Content content) {
        return ResponseEntity.ok(contentService.createContent(content));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Content> updateContent(@PathVariable Long id, @RequestBody Content updatedContent) {
        return ResponseEntity.ok(contentService.updateContent(id, updatedContent));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteContent(@PathVariable Long id) {
        contentService.deleteContent(id);
        return ResponseEntity.noContent().build();
    }
}
