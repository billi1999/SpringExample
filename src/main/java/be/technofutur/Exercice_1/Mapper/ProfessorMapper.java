package be.technofutur.Exercice_1.Mapper;

import be.technofutur.Exercice_1.Entities.Professor;
import be.technofutur.Exercice_1.Repository.SectionRepository;
import be.technofutur.Exercice_1.dto.ProfessorDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
@Component
public class ProfessorMapper implements Mapper<ProfessorDTO, Professor>{
    @Autowired
    private SectionSimplifiedMapper sectionMapper;
    @Autowired
    private SectionRepository repository;

    @Override
    public ProfessorDTO toDTO(Professor entity) {
        if(entity == null){
            return null;
        }
        return ProfessorDTO.builder()
                .id(entity.getId())
                .section(sectionMapper.toDTO(entity.getSection()))
                .name(entity.getName())
                .hireDate(entity.getHireDate())
                .mail(entity.getMail())
                .office(entity.getOffice())
                .surname(entity.getSurname())
                .wage(entity.getWage())
                .build();

    }

    @Override
    public Professor toEntity(ProfessorDTO dto) {
        if (dto ==null){
            return null;
        }
        return Professor.builder()
                .id(dto.getId())
                .hireDate(dto.getHireDate())
                .mail(dto.getMail())
                .name(dto.getName())
                .section(repository.getOne(dto.getSection().getId()))
                .office(dto.getOffice())
                .build();

    }
}
