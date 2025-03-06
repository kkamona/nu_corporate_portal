package edu.nu.corporate_portal.repository;

import edu.nu.corporate_portal.models.UserRights;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRightsRepository extends JpaRepository<UserRights, Long> {
    Optional<UserRights> findByUserId(Long userId);
}
