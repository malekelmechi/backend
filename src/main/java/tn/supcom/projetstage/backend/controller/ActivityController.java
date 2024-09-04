package tn.supcom.projetstage.backend.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tn.supcom.projetstage.backend.dto.ActivityDTO;
import tn.supcom.projetstage.backend.entity.*;
import tn.supcom.projetstage.backend.repository.*;
import tn.supcom.projetstage.backend.service.ActivityService;

import java.util.List;

@RestController
@RequestMapping("/api/activities")
public class ActivityController {

    @Autowired
    private ActivityService activityService;
    @Autowired
    private ActivityRepository activityRepository;

    @Autowired
    private SubjectRepository subjectRepository;

    @Autowired
    private TeacherRepository teacherRepository;

    @Autowired
    private GroupRepository groupRepository;

    @Autowired
    private RoomRepository roomRepository;

    @GetMapping
    public List<ActivityDTO> getAllActivities() {
        return activityService.getAllActivitiesWithDetails();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ActivityDTO> getActivityById(@PathVariable Long id) {
        Activity activity = activityService.getActivityById(id);
        if (activity != null) {
            ActivityDTO dto = new ActivityDTO();
            dto.setId(activity.getId());
            dto.setTeacherName(activity.getTeacher() != null
                    ? activity.getTeacher().getFirstName() + " " + activity.getTeacher().getLastName()
                    : "N/A");
            dto.setRoomName(activity.getRoom() != null ? activity.getRoom().getName() : "N/A");
            dto.setSubjectName(activity.getSubject() != null ? activity.getSubject().getName() : "N/A");
            dto.setGroupName(activity.getGroup() != null ? activity.getGroup().getName() : "N/A");
            return ResponseEntity.ok(dto);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<Activity> createActivity(@RequestBody ActivityDTO activityDTO) {
        try {
            Activity activity = activityService.createActivity(convertDTOToEntity(activityDTO));
            return ResponseEntity.ok(activity);
        } catch (Exception e) {
            return ResponseEntity.status(500).body(null);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Activity> updateActivity(@PathVariable Long id, @RequestBody ActivityDTO activityDTO) {
        try {
            Activity activity = activityService.updateActivity(id, convertDTOToEntity(activityDTO));
            return ResponseEntity.ok(activity);
        } catch (Exception e) {
            return ResponseEntity.status(500).body(null);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteActivity(@PathVariable Long id) {
        try {
            activityService.deleteActivity(id);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return ResponseEntity.status(500).build();
        }
    }

    private Activity convertDTOToEntity(ActivityDTO dto) {
        Activity activity = new Activity();


        if (dto.getSubjectName() != null) {
            Subject subject = subjectRepository.findByName(dto.getSubjectName())
                    .orElseThrow(() -> new RuntimeException("Subject not found: " + dto.getSubjectName()));
            activity.setSubject(subject);
        }

        if (dto.getTeacherName() != null) {
            String[] nameParts = dto.getTeacherName().split(" ");
            if (nameParts.length >= 2) {
                Teacher teacher = teacherRepository.findByFirstNameAndLastName(nameParts[0], nameParts[1])
                        .orElseThrow(() -> new RuntimeException("Teacher not found: " + dto.getTeacherName()));
                activity.setTeacher(teacher);
            } else {
                throw new RuntimeException("Teacher name format is incorrect: " + dto.getTeacherName());
            }
        }

        if (dto.getGroupName() != null) {
            Group group = groupRepository.findByName(dto.getGroupName())
                    .orElseThrow(() -> new RuntimeException("Group not found: " + dto.getGroupName()));
            activity.setGroup(group);
        }


        if (dto.getRoomName() != null) {
            Room room = roomRepository.findByName(dto.getRoomName())
                    .orElseThrow(() -> new RuntimeException("Room not found: " + dto.getRoomName()));
            activity.setRoom(room);
        }

        return activity;
    }
}
