package edu.nu.corporate_portal.repository;

import edu.nu.corporate_portal.models.Phonebook;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PhonebookRepository extends JpaRepository<Phonebook, Long> {

    // Retrieve all phonebook entries for a specific user
    List<Phonebook> findByUserId(Long userId);

    // Retrieve all phonebook entries of a specific type (e.g., "emergency_contact", "service_provider", "reference")
    List<Phonebook> findByType(String type);
}
