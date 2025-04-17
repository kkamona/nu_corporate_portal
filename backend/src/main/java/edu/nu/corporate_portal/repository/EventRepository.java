package edu.nu.corporate_portal.repository;

import edu.nu.corporate_portal.models.Event;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface EventRepository extends JpaRepository<Event, Long> {
    List<Event> findByStartDateBetween(LocalDate from, LocalDate to);
}
