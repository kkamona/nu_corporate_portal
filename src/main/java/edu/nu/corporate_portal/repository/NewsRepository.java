package edu.nu.corporate_portal.repository;

import edu.nu.corporate_portal.models.News;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface NewsRepository extends JpaRepository<News, Long> {
    List<News> findByUserId(Long userId);
    List<News> findByPublishedDate(LocalDate publishedDate);
}
