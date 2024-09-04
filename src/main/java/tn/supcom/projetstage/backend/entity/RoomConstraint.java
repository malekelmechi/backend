package tn.supcom.projetstage.backend.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "room_constraint")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class RoomConstraint {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String room;

    @ManyToOne
    @JoinColumn(name = "teacher_id", nullable = false)
    private Teacher teacher;

    public RoomConstraint(String room, Teacher teacher) {
        this.room = room;
        this.teacher = teacher;}


        public String getRoom() {
            return room;
        }

        public void setRoom(String room) {
            this.room = room;
        }    }


