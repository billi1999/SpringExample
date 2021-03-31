package be.technofutur.Exercice_1.dto;

import be.technofutur.Exercice_1.Entities.Section;
import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.*;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ProfessorDTO implements IdentifiedDTO<Integer>{
    Integer id;
    String name;
    String surname;
    SectionSimplified section;
    int office;
    String mail;
    LocalDateTime hireDate;
    int wage;
}
