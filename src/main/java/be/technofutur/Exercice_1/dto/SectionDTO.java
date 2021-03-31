package be.technofutur.Exercice_1.dto;

import lombok.*;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class SectionDTO implements IdentifiedDTO<Integer> {
    private Integer id;

    private String section_name;

    private int delegate_id;
    private List<StudentSimplified> students;
    private List<ProfessorSimplified> professor;
}
