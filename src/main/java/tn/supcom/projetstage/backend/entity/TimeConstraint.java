package tn.supcom.projetstage.backend.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "time_constraint")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class TimeConstraint {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String day;
    private String period;

    @ManyToOne
    @JoinColumn(name = "teacher_id", nullable = false)
    @JsonIgnore
    private Teacher teacher;

    public TimeConstraint(String day, String period, Teacher teacher) {
        this.day = day;
        this.period = period;
        this.teacher = teacher;
    }
}
