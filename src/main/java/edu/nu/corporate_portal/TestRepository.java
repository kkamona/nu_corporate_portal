package edu.nu.corporate_portal;

import edu.nu.corporate_portal.TestEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TestRepository extends JpaRepository<TestEntity, Long> {
}
