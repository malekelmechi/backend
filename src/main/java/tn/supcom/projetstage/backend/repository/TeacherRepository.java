package tn.supcom.projetstage.backend.repository;
import org.springframework.stereotype.Repository;
import  tn.supcom.projetstage.backend.entity.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


@Repository
public interface TeacherRepository extends JpaRepository <Teacher, Long>{
    Teacher findByUsernameAndPassword(String username, String password);
    Optional<Teacher> findByFirstNameAndLastName(String firstName, String lastName);
}

