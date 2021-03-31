package be.technofutur.Exercice_1.Mapper;

import be.technofutur.Exercice_1.Entities.Professor;
import be.technofutur.Exercice_1.Entities.Section;
import be.technofutur.Exercice_1.Repository.ProfessorRepository;
import be.technofutur.Exercice_1.dto.ProfessorSimplified;
import be.technofutur.Exercice_1.dto.SectionDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class SectionMapper implements Mapper<SectionDTO, Section>{
    @Autowired
    private StudentSectionMapper StudentMapper;

    private ProfessorRepository repository;

    @Override
    public SectionDTO toDTO(Section entity) {
        if(entity ==null){
            return null;
        }
       // List<Professor> list=repository.
        return SectionDTO.builder()
                .id(entity.getId())
                .section_name(entity.getSection_name())
                .delegate_id(entity.getDelegate_id())
                .students(entity.getStudents().stream()
                        .map(StudentMapper::toDTO)
                        .collect(Collectors.toList()))
                //.professor()
                .build();
    }

    @Override
    public Section toEntity(SectionDTO sectionDTO) {
        if (sectionDTO == null){
            return null;
        }
        return Section.builder()
                .id(sectionDTO.getId())
                .section_name(sectionDTO.getSection_name())
                .delegate_id(sectionDTO.getDelegate_id())
                .build();
    }
}
