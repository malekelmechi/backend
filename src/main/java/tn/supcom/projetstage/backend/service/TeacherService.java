package tn.supcom.projetstage.backend.service;

import tn.supcom.projetstage.backend.entity.Teacher;
import tn.supcom.projetstage.backend.repository.TeacherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TeacherService {

    @Autowired
    private TeacherRepository teacherRepository;

    public List<Teacher> getAllTeachers() {
        return teacherRepository.findAll();
    }

    public Teacher getTeacherById(Long id) {
        return teacherRepository.findById(id).orElseThrow(() -> new RuntimeException("Teacher not found"));
    }

    public Teacher createTeacher(Teacher teacher) {
        if (teacher.getRole() == null || teacher.getRole().isEmpty()) {
            teacher.setRole("user");
        }
        return teacherRepository.save(teacher);
    }

    public Teacher updateTeacher(Long id, Teacher teacherDetails) {
        Teacher teacher = teacherRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Teacher not found"));
        teacher.setFirstName(teacherDetails.getFirstName());
        teacher.setLastName(teacherDetails.getLastName());
        teacher.setEmail(teacherDetails.getEmail());
        teacher.setAddress(teacherDetails.getAddress());
        teacher.setPhoneNumber(teacherDetails.getPhoneNumber());
        teacher.setUsername(teacherDetails.getUsername());
        teacher.setPassword(teacherDetails.getPassword());
        teacher.setRole(teacherDetails.getRole());
        return teacherRepository.save(teacher);
    }

    public void deleteTeacher(Long id) {
        if (!teacherRepository.existsById(id)) {
            throw new RuntimeException("Teacher not found");
        }
        teacherRepository.deleteById(id);
    }

    public Teacher authenticateTeacher(String username, String password) {
        return teacherRepository.findByUsernameAndPassword(username, password);
    }
}
