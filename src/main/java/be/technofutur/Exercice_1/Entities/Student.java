package be.technofutur.Exercice_1.Entities;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Student {
    @Id
    @Column(name = "student_id")
    private int id;
    @Column(name = "first_name")
    private String firstName;
    @Column(name = "last_name")
    private String lastName;
    @Column(name = "birth_date")
    private LocalDateTime birthDate;
    @Column
    private String login;

    @ManyToOne
    @JoinColumn(name = "section_id")
    private Section section;
    @Column(name = "year_result")
    private int yearResult;
    @Column(name = "course_id")
    private String courseId;
}
