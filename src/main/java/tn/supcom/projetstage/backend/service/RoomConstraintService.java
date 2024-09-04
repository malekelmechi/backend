package tn.supcom.projetstage.backend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.supcom.projetstage.backend.dto.RoomConstraintDTO;
import tn.supcom.projetstage.backend.entity.RoomConstraint;
import tn.supcom.projetstage.backend.entity.Teacher;
import tn.supcom.projetstage.backend.repository.RoomConstraintRepository;
import tn.supcom.projetstage.backend.repository.TeacherRepository;

import java.util.List;
import java.util.Optional;
@Service
public class RoomConstraintService {

    @Autowired
    private RoomConstraintRepository roomConstraintRepository;

    @Autowired
    private TeacherRepository teacherRepository;

    public List<RoomConstraint> getAllRoomConstraints() {
        return roomConstraintRepository.findAll();
    }

    public List<RoomConstraint> getRoomConstraintsByTeacher(Long teacherId) {
        return roomConstraintRepository.findByTeacherId(teacherId);
    }

    public RoomConstraint createRoomConstraint(RoomConstraintDTO roomConstraintDTO, Long teacherId) {
        System.out.println("Room received: " + roomConstraintDTO.getRoom());
        Optional<Teacher> teacherOptional = teacherRepository.findById(teacherId);
        if (!teacherOptional.isPresent()) {
            throw new IllegalArgumentException("Teacher not found with ID: " + teacherId);
        }

        Teacher teacher = teacherOptional.get();
        RoomConstraint roomConstraint = new RoomConstraint(roomConstraintDTO.getRoom(), teacher);

        return roomConstraintRepository.save(roomConstraint);
    }

}
