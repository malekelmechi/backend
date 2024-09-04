package tn.supcom.projetstage.backend.repository;
import org.springframework.stereotype.Repository;
import  tn.supcom.projetstage.backend.entity.Group;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


@Repository

public interface GroupRepository extends JpaRepository<Group, Long> {
    Optional<Group> findByName(String name);
}
