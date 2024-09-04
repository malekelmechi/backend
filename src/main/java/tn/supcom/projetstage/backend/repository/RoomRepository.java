package tn.supcom.projetstage.backend.repository;
import org.springframework.stereotype.Repository;
import  tn.supcom.projetstage.backend.entity.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import tn.supcom.projetstage.backend.entity.RoomConstraint;
import tn.supcom.projetstage.backend.entity.Teacher;


import java.util.List;
import java.util.Optional;

@Repository
public interface RoomRepository extends JpaRepository <Room, Long>{
    Optional<Room> findByName(String name);
}

