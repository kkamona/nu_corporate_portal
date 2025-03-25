package edu.nu.corporate_portal.controllers;

import edu.nu.corporate_portal.DTO.News.NewsGetDTO;
import edu.nu.corporate_portal.DTO.News.NewsPatchDTO;
import edu.nu.corporate_portal.DTO.News.NewsPostDTO;
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
    public ResponseEntity<List<NewsGetDTO>> getAllNews() {
        List<NewsGetDTO> newsList = newsService.getAllNews()
                .stream()
                .map(NewsGetDTO::new)
                .toList();
        return ResponseEntity.ok(newsList);
    }

    @GetMapping("/{id}")
    public ResponseEntity<NewsGetDTO> getNewsById(@PathVariable Long id) {
        return ResponseEntity.ok(new NewsGetDTO(newsService.getNewsById(id)));
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<NewsGetDTO>> getNewsByUserId(@PathVariable Long userId) {
        List<NewsGetDTO> newsList = newsService.getNewsByUserId(userId)
                .stream()
                .map(NewsGetDTO::new)
                .toList();
        return ResponseEntity.ok(newsList);
    }

    @GetMapping("/date/{date}")
    public ResponseEntity<List<NewsGetDTO>> getNewsByPublishedDate(@PathVariable String date) {
        LocalDate publishedDate = LocalDate.parse(date);
        List<NewsGetDTO> newsList = newsService.getNewsByPublishedDate(publishedDate)
                .stream()
                .map(NewsGetDTO::new)
                .toList();
        return ResponseEntity.ok(newsList);
    }

    @PostMapping
    public ResponseEntity<NewsGetDTO> createNews(@RequestBody NewsPostDTO dto) {
        News news = newsService.createNews(dto);
        return ResponseEntity.ok(new NewsGetDTO(news));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<NewsGetDTO> updateNews(@PathVariable Long id, @RequestBody NewsPatchDTO dto) {
        News updatedNews = newsService.updateNews(id, dto);
        return ResponseEntity.ok(new NewsGetDTO(updatedNews));
    }

    @PostMapping("/{id}/publish")
    public ResponseEntity<NewsGetDTO> publishNews(@PathVariable Long id) {
        News publishedNews = newsService.publishNews(id);
        return ResponseEntity.ok(new NewsGetDTO(publishedNews));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteNews(@PathVariable Long id) {
        newsService.deleteNews(id);
        return ResponseEntity.noContent().build();
    }
}
