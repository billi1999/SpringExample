package be.technofutur.Exercice_1.dto;

import be.technofutur.Exercice_1.constraints.OldTimeResult;
import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.validation.constraints.*;
import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
//va permettre de mettre tout les champs en privé de base
@FieldDefaults(level = AccessLevel.PRIVATE)
@OldTimeResult
public class StudentDTO implements IdentifiedDTO<Integer> {
     @NotNull
     Integer id;
     @Size(min = 2, max = 20)
     String firstName;
     @NotBlank // @NotEmpty
     String lastName;
     @PastOrPresent // @Past // @Future // @FuturOrPresent
     LocalDateTime birthDate;
     //@Pattern(regexp = "")
     //@Email
     String login;
     SectionSimplified section;
     @NotNull
     @Min(0) @Max(20)
     // @Positive // @Negative // NegativeOrZero // PositiveOrZero
     Integer yearResult;
     @NotNull
     String courseId;

     //    @AssertFalse // @AssertTrue
     //    private boolean bool;
}
