package tn.supcom.projetstage.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import tn.supcom.projetstage.backend.entity.RoomConstraint;

import java.util.List;

@Repository
public interface RoomConstraintRepository extends JpaRepository<RoomConstraint, Long> {

    List<RoomConstraint> findByTeacherId(Long teacherId);

    List<Long> findRoomIdsByTeacherId(Long teacherId);
}
