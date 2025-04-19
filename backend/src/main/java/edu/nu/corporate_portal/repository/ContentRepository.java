package edu.nu.corporate_portal.repository;

import edu.nu.corporate_portal.models.Content;
import edu.nu.corporate_portal.models.ContentType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ContentRepository extends JpaRepository<Content, Long> {
}
