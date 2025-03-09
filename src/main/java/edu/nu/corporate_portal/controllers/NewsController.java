package edu.nu.corporate_portal.controllers;

import edu.nu.corporate_portal.models.News;
import edu.nu.corporate_portal.services.NewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/news")
public class NewsController {

    private final NewsService newsService;

    @Autowired
    public NewsController(NewsService newsService) {
        this.newsService = newsService;
    }

    @GetMapping
    public ResponseEntity<List<News>> getAllNews() {
        return ResponseEntity.ok(newsService.getAllNews());
    }

    @GetMapping("/{id}")
    public ResponseEntity<News> getNewsById(@PathVariable Long id) {
        return ResponseEntity.ok(newsService.getNewsById(id));
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<News>> getNewsByUserId(@PathVariable Long userId) {
        return ResponseEntity.ok(newsService.getNewsByUserId(userId));
    }

    @GetMapping("/date/{date}")
    public ResponseEntity<List<News>> getNewsByPublishedDate(@PathVariable String date) {
        LocalDate publishedDate = LocalDate.parse(date); // Expects format "yyyy-MM-dd"
        return ResponseEntity.ok(newsService.getNewsByPublishedDate(publishedDate));
    }

    @PostMapping
    public ResponseEntity<News> createNews(@RequestBody News news) {
        return ResponseEntity.ok(newsService.createNews(news));
    }

    @PutMapping("/{id}")
    public ResponseEntity<News> updateNews(@PathVariable Long id, @RequestBody News updatedNews) {
        return ResponseEntity.ok(newsService.updateNews(id, updatedNews));
    }

    // New endpoint for publishing news
    @PutMapping("/{id}/publish")
    public ResponseEntity<News> publishNews(@PathVariable Long id) {
        return ResponseEntity.ok(newsService.publishNews(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteNews(@PathVariable Long id) {
        newsService.deleteNews(id);
        return ResponseEntity.noContent().build();
    }
}
