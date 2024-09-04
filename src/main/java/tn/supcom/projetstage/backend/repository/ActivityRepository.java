package tn.supcom.projetstage.backend.repository;
import org.springframework.stereotype.Repository;
import  tn.supcom.projetstage.backend.entity.Activity;
import org.springframework.data.jpa.repository.JpaRepository;
@Repository
public interface ActivityRepository extends JpaRepository<Activity, Long>  {
}
