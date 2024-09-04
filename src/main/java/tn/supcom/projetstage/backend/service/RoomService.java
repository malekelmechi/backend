package tn.supcom.projetstage.backend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.supcom.projetstage.backend.entity.Room;
import tn.supcom.projetstage.backend.repository.RoomRepository;
import tn.supcom.projetstage.backend.repository.RoomConstraintRepository;

import java.util.List;

@Service
public class RoomService {

    @Autowired
    private RoomRepository R;

    @Autowired
    private RoomConstraintRepository roomConstraintRepository;

    public List<Room> getAllRooms() {
        return R.findAll();
    }

    public Room getRoomById(Long id) {
        return R.findById(id).orElse(null);
    }

    public Room createRoom(Room room) {
        return R.save(room);
    }

    public Room updateRoom(Long id, Room roomDetails) {
        Room room = R.findById(id).orElse(null);
        if (room != null) {
            room.setName(roomDetails.getName());
            room.setCapacity(roomDetails.getCapacity());
            return R.save(room);
        }
        return null;
    }

    public void deleteRoom(Long id) {
        R.deleteById(id);
    }

    public List<Room> getRoomsWithConstraints(Long teacherId) {

        List<Long> roomIds = roomConstraintRepository.findRoomIdsByTeacherId(teacherId);


        return R.findAllById(roomIds);
    }
}
