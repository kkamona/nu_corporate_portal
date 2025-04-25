package edu.nu.corporate_portal.repository;

import edu.nu.corporate_portal.models.Club;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClubRepository extends JpaRepository<Club, Long> {
    List<Club> findByMembers_Id(Long userId);
}
