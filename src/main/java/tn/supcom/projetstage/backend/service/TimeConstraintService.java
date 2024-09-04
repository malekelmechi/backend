package tn.supcom.projetstage.backend.service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.supcom.projetstage.backend.dto.TimeConstraintDTO;
import tn.supcom.projetstage.backend.entity.TimeConstraint;
import tn.supcom.projetstage.backend.entity.Teacher;
import tn.supcom.projetstage.backend.repository.TimeConstraintRepository;
import tn.supcom.projetstage.backend.repository.TeacherRepository;

import java.util.List;
import java.util.Optional;

@Service
public class TimeConstraintService {

    @Autowired
    private TimeConstraintRepository timeConstraintRepository;

    @Autowired
    private TeacherRepository teacherRepository;

    public List<TimeConstraint> getAllTimeConstraints() {
        return timeConstraintRepository.findAll();
    }

    public TimeConstraint createTimeConstraint(TimeConstraintDTO timeConstraintDTO, Long teacherId) {

        Optional<Teacher> teacherOptional = teacherRepository.findById(teacherId);
        if (!teacherOptional.isPresent()) {
            throw new IllegalArgumentException("Teacher not found with ID: " + teacherId);
        }

        Teacher teacher = teacherOptional.get();
        TimeConstraint timeConstraint = new TimeConstraint(
                timeConstraintDTO.getDay(),
                timeConstraintDTO.getPeriod(),
                teacher
        );

        return timeConstraintRepository.save(timeConstraint);
    }

    public List<TimeConstraint> getTimeConstraintsByTeacher(Long teacherId) {
        return timeConstraintRepository.findByTeacherId(teacherId);
    }
    public void deleteTimeConstraint(TimeConstraintDTO timeConstraintDTO) {
        List<TimeConstraint> constraints = timeConstraintRepository.findByTeacherIdAndDayAndPeriod(
                timeConstraintDTO.getTeacherId(),
                timeConstraintDTO.getDay(),
                timeConstraintDTO.getPeriod()
        );

        if (constraints.isEmpty()) {
            throw new IllegalArgumentException("No time constraints found for the given teacher, day, and period");
        }

        timeConstraintRepository.deleteAll(constraints);
    }
}
