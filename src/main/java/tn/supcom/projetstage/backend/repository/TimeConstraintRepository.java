package tn.supcom.projetstage.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tn.supcom.projetstage.backend.entity.TimeConstraint;

import java.util.List;

@Repository
public interface TimeConstraintRepository extends JpaRepository<TimeConstraint, Long> {
    List<TimeConstraint> findByTeacherId(Long teacherId);
    List<TimeConstraint> findByTeacherIdAndDayAndPeriod(Long teacherId, String day, String period);
}
