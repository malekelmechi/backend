package tn.supcom.projetstage.backend.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tn.supcom.projetstage.backend.dto.RoomConstraintDTO;
import tn.supcom.projetstage.backend.entity.RoomConstraint;
import tn.supcom.projetstage.backend.service.RoomConstraintService;
import java.util.List;
@RestController
@RequestMapping("/api/room-constraints")
public class RoomConstraintController {

    @Autowired
    private RoomConstraintService roomConstraintService;

    @GetMapping
    public ResponseEntity<List<RoomConstraint>> getAllRoomConstraints() {
        List<RoomConstraint> roomConstraints = roomConstraintService.getAllRoomConstraints();
        return ResponseEntity.ok(roomConstraints);
    }

    @PostMapping("/{teacherId}")
    public ResponseEntity<?> createRoomConstraint(
            @RequestBody RoomConstraintDTO roomConstraintDTO,
            @PathVariable Long teacherId) {
        try {
            RoomConstraint createdRoomConstraint = roomConstraintService.createRoomConstraint(roomConstraintDTO, teacherId);
            return ResponseEntity.ok(createdRoomConstraint);
        } catch (Exception e) {
            return ResponseEntity.status(400).body("Error creating room constraint: " + e.getMessage());
        }
    }


    @GetMapping("/teacher/{teacherId}")
    public ResponseEntity<List<RoomConstraint>> getRoomConstraintsByTeacher(@PathVariable Long teacherId) {
        List<RoomConstraint> roomConstraints = roomConstraintService.getRoomConstraintsByTeacher(teacherId);
        return ResponseEntity.ok(roomConstraints);
    }
}
