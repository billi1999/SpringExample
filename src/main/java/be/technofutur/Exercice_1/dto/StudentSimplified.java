package be.technofutur.Exercice_1.dto;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level= AccessLevel.PRIVATE)
@Builder
//Ce que c'est
//Pour qui c'est
public class StudentSimplified {

    Integer id;
    String firstname;
    String lastname;
}
