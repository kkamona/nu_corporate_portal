package edu.nu.corporate_portal.services;

import edu.nu.corporate_portal.DTO.News.NewsPatchDTO;
import edu.nu.corporate_portal.DTO.News.NewsPostDTO;
import edu.nu.corporate_portal.models.News;
import edu.nu.corporate_portal.models.User;
import edu.nu.corporate_portal.repository.NewsRepository;
import edu.nu.corporate_portal.repository.UserRepository;
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
    private final UserRepository userRepository;

    @Autowired
    public NewsService(NewsRepository newsRepository, UserRepository userRepository) {
        this.newsRepository = newsRepository;
        this.userRepository = userRepository;
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

    public List<News> getNewsByPublishedDate(LocalDate date) {
        return newsRepository.findByPublishedDate(date);
    }

    public News createNews(NewsPostDTO dto) {
        User user = userRepository.findById(dto.getUserId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found"));

        News news = new News();
        news.setUser(user);
        news.setTitle(dto.getTitle());
        news.setContent(dto.getContent());
        news.setCreatedAt(LocalDateTime.now());
        news.setUpdatedAt(LocalDateTime.now());

        return newsRepository.save(news);
    }

    public News updateNews(Long id, NewsPatchDTO dto) {
        News news = getNewsById(id);

        if (dto.getTitle() != null) news.setTitle(dto.getTitle());
        if (dto.getContent() != null) news.setContent(dto.getContent());
        if (dto.getPublishedDate() != null) news.setPublishedDate(dto.getPublishedDate());

        news.setUpdatedAt(LocalDateTime.now());

        return newsRepository.save(news);
    }

    public void deleteNews(Long id) {
        News existingNews = getNewsById(id);
        newsRepository.delete(existingNews);
    }

    public News publishNews(Long id) {
        News news = getNewsById(id);
        if (news.getPublishedDate() == null) {
            news.setPublishedDate(LocalDate.now());
        }
        news.setUpdatedAt(LocalDateTime.now());
        return newsRepository.save(news);
    }
}
