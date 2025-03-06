package edu.nu.corporate_portal.repository;

import edu.nu.corporate_portal.models.BirthdayWidget;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Collection;
import java.util.List;

@Repository
public interface BirthdayWidgetRepository extends JpaRepository<BirthdayWidget, Long> {
    List<BirthdayWidget> findByBirthDate(LocalDate birthDate);
    List<BirthdayWidget> findByUser_Id(Long userId);
}
