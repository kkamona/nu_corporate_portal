package edu.nu.corporate_portal.repository;

import edu.nu.corporate_portal.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);

//    Optional<User> findByAzureSsoId(String azureSsoId);

    boolean existsByEmail(String email);

}
