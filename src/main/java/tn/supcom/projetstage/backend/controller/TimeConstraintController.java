package tn.supcom.projetstage.backend.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tn.supcom.projetstage.backend.dto.TimeConstraintDTO;
import tn.supcom.projetstage.backend.entity.TimeConstraint;
import tn.supcom.projetstage.backend.service.TimeConstraintService;
import tn.supcom.projetstage.backend.service.TeacherService;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/time-constraints")
public class TimeConstraintController {

    @Autowired
    private TimeConstraintService timeConstraintService;

    @Autowired
    private TeacherService teacherService;

    @GetMapping
    public ResponseEntity<List<TimeConstraintDTO>> getAllTimeConstraints() {
        List<TimeConstraint> timeConstraints = timeConstraintService.getAllTimeConstraints();
        List<TimeConstraintDTO> timeConstraintDTOs = timeConstraints.stream().map(tc -> {
            TimeConstraintDTO dto = new TimeConstraintDTO();
            dto.setDay(tc.getDay());
            dto.setPeriod(tc.getPeriod());
            dto.setTeacherId(tc.getTeacher().getId());
            return dto;
        }).collect(Collectors.toList());

        return ResponseEntity.ok(timeConstraintDTOs);
    }

    @PostMapping("/{teacherId}")
    public ResponseEntity<?> createTimeConstraint(
            @RequestBody TimeConstraintDTO timeConstraintDTO,
            @PathVariable Long teacherId) {
        try {
            TimeConstraint createdTimeConstraint = timeConstraintService.createTimeConstraint(timeConstraintDTO, teacherId);
            return ResponseEntity.ok(createdTimeConstraint);
        } catch (Exception e) {
            return ResponseEntity.status(400).body("Error creating time constraint: " + e.getMessage());
        }
    }

    @GetMapping("/teacher/{teacherId}")
    public ResponseEntity<List<TimeConstraintDTO>> getTimeConstraintsByTeacher(@PathVariable Long teacherId) {
        List<TimeConstraint> timeConstraints = timeConstraintService.getTimeConstraintsByTeacher(teacherId);
        List<TimeConstraintDTO> timeConstraintDTOs = timeConstraints.stream().map(tc -> {
            TimeConstraintDTO dto = new TimeConstraintDTO();
            dto.setDay(tc.getDay());
            dto.setPeriod(tc.getPeriod());
            dto.setTeacherId(tc.getTeacher().getId());
            return dto;
        }).collect(Collectors.toList());

        return ResponseEntity.ok(timeConstraintDTOs);
    }
    @DeleteMapping
    public ResponseEntity<?> deleteTimeConstraint(@RequestBody TimeConstraintDTO timeConstraintDTO) {
        try {
            timeConstraintService.deleteTimeConstraint(timeConstraintDTO);
            return ResponseEntity.ok("Time constraint deleted successfully");
        } catch (Exception e) {
            return ResponseEntity.status(400).body("Error deleting time constraint: " + e.getMessage());
        }
    }
}
