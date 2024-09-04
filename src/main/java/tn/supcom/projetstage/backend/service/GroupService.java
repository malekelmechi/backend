package tn.supcom.projetstage.backend.service;
import tn.supcom.projetstage.backend.entity.Group;
import tn.supcom.projetstage.backend.repository.GroupRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GroupService {

    @Autowired
    GroupRepository G;

    public List<Group> getAllGroups() {
        return G.findAll();
    }

    public Group getGroupById (Long id) {
        return G.findById(id).orElse(null);
    }

    public Group createGroup(Group group) {
        return G.save(group);
    }

    public Group updateGroup(Long id, Group groupDetails) {
        Group group = G.findById(id).orElse(null);
        if (group != null) {
            group.setName(groupDetails.getName());
            group.setStudentCount(groupDetails.getStudentCount());
            return G.save(group);
        }
        return null;
    }

    public void deleteGroup(Long id) {
        G.deleteById(id);
    }
}
