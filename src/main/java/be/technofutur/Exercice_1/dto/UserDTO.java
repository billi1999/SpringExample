package be.technofutur.Exercice_1.dto;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserDTO {

    Integer id;

    String username;

    String password;

    List<String> roles;

}
