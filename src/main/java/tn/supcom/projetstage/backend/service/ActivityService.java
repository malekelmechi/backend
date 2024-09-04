package tn.supcom.projetstage.backend.service;
import tn.supcom.projetstage.backend.dto.ActivityDTO;
import tn.supcom.projetstage.backend.entity.Activity;
import tn.supcom.projetstage.backend.entity.Subject;
import tn.supcom.projetstage.backend.entity.Teacher;
import tn.supcom.projetstage.backend.entity.Group;
import tn.supcom.projetstage.backend.entity.Room;
import tn.supcom.projetstage.backend.repository.ActivityRepository;
import tn.supcom.projetstage.backend.repository.SubjectRepository;
import tn.supcom.projetstage.backend.repository.TeacherRepository;
import tn.supcom.projetstage.backend.repository.GroupRepository;
import tn.supcom.projetstage.backend.repository.RoomRepository;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ActivityService {

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

    public List<ActivityDTO> getAllActivitiesWithDetails() {
        List<Activity> activities = activityRepository.findAll();
        return activities.stream().map(activity -> {
            ActivityDTO dto = new ActivityDTO();
            dto.setId(activity.getId());

            String teacherName = "N/A";
            if (activity.getTeacher() != null) {
                Teacher teacher = teacherRepository.findById(activity.getTeacher().getId()).orElse(null);
                if (teacher != null) {
                    teacherName = teacher.getFirstName() + " " + teacher.getLastName();
                }
            }
            dto.setTeacherName(teacherName);


            dto.setRoomName(activity.getRoom() != null ? activity.getRoom().getName() : "N/A");
            dto.setSubjectName(activity.getSubject() != null ? activity.getSubject().getName() : "N/A");
            dto.setGroupName(activity.getGroup() != null ? activity.getGroup().getName() : "N/A");

            return dto;
        }).collect(Collectors.toList());
    }


    public Activity getActivityById(Long id) {
        return activityRepository.findById(id).orElse(null);
    }
    private void validateAndSetAssociations(Activity activity) {
        if (activity.getSubject() != null) {
            Subject subject = subjectRepository.findById(activity.getSubject().getId())
                    .orElseThrow(() -> new RuntimeException("Subject not found"));
            activity.setSubject(subject);
        }

        if (activity.getTeacher() != null) {
            Teacher teacher = teacherRepository.findById(activity.getTeacher().getId())
                    .orElseThrow(() -> new RuntimeException("Teacher not found"));
            activity.setTeacher(teacher);
        }

        if (activity.getGroup() != null) {
            Group group = groupRepository.findById(activity.getGroup().getId())
                    .orElseThrow(() -> new RuntimeException("Group not found"));
            activity.setGroup(group);
        }

        if (activity.getRoom() != null) {
            Room room = roomRepository.findById(activity.getRoom().getId())
                    .orElseThrow(() -> new RuntimeException("Room not found"));
            activity.setRoom(room);
        }
    }

    public Activity createActivity(Activity activity) {
        validateAndSetAssociations(activity);
        return activityRepository.save(activity);
    }

    public Activity updateActivity(Long id, Activity activityDetails) {
        Activity activity = activityRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Activity not found"));

        validateAndSetAssociations(activityDetails);
        activity.setSubject(activityDetails.getSubject());
        activity.setTeacher(activityDetails.getTeacher());
        activity.setGroup(activityDetails.getGroup());
        activity.setRoom(activityDetails.getRoom());

        return activityRepository.save(activity);
    }

    public void deleteActivity(Long id) {
        if (!activityRepository.existsById(id)) {
            throw new RuntimeException("Activity not found");
        }
        activityRepository.deleteById(id);
    }


}

