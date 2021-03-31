package be.technofutur.Exercice_1.Entities;

import be.technofutur.Exercice_1.constraints.Multiple4;
import be.technofutur.Exercice_1.constraints.Waged;
import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Waged
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Professor {
    @Id
    @Column(name = "professor_id")
    @NotNull
    Integer id;

    @Column(name = "professor_name")
    @NotNull
    //@Size(min = 3)
    String name;

    @Column(name = "professor_surname")
    @NotNull
    //@Size(min = 3)
    String surname;


    @ManyToOne
    @JoinColumn(name = "section_id")

    //@NotNull
    Section section;

    @Column(name = "professor_office")
    @Min(100) @Max(999)
    @NotNull
    @Multiple4
    int office;

    @Column(name = "professor_email")
    @Email
    @NotNull
    String mail;

    @Column(name = "professor_hire_date")
    @PastOrPresent
    @NotNull
    LocalDateTime hireDate;

    @Column(name = "professor_wage")
    @Min(1500)
    @NotNull
    int wage;

}
