package edu.nu.corporate_portal.repository;

import edu.nu.corporate_portal.models.NuCalendar;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface NuCalendarRepository extends JpaRepository<NuCalendar, Long> {
    List<NuCalendar> findByEventDate(LocalDate eventDate);
    List<NuCalendar> findByCreatedBy(Long createdBy);

}
