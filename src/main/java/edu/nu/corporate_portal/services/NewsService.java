package edu.nu.corporate_portal.services;

import edu.nu.corporate_portal.models.News;
import edu.nu.corporate_portal.repository.NewsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class NewsService {

    private final NewsRepository newsRepository;

    @Autowired
    public NewsService(NewsRepository newsRepository) {
        this.newsRepository = newsRepository;
    }

    public List<News> getAllNews() {
        return newsRepository.findAll();
    }

    public News getNewsById(Long id) {
        return newsRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "News not found"));
    }

    public List<News> getNewsByUserId(Long userId) {
        return newsRepository.findByUserId(userId);
    }

    public boolean newsExistsForUser(Long userId) {
        List<News> newsList = getNewsByUserId(userId);
        return newsList != null && !newsList.isEmpty();
    }

    public List<News> getNewsByPublishedDate(LocalDate date) {
        return newsRepository.findByPublishedDate(date);
    }

    public News createNews(News news) {
        news.setCreatedAt(LocalDateTime.now());
        news.setUpdatedAt(LocalDateTime.now());
        return newsRepository.save(news);
    }

    public News updateNews(Long id, News updatedNews) {
        News existingNews = getNewsById(id);
        existingNews.setTitle(updatedNews.getTitle());
        existingNews.setContent(updatedNews.getContent());
        existingNews.setPublishedDate(updatedNews.getPublishedDate());
        existingNews.setUpdatedAt(LocalDateTime.now());
        return newsRepository.save(existingNews);
    }

    public void deleteNews(Long id) {
        News existingNews = getNewsById(id);
        newsRepository.delete(existingNews);
    }
}
