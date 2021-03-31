package be.technofutur.Exercice_1.Mapper;

import be.technofutur.Exercice_1.Entities.Student;
import be.technofutur.Exercice_1.dto.StudentSimplified;
import org.springframework.stereotype.Component;

@Component
public class StudentSectionMapper {
    StudentSimplified toDTO(Student entity){
        if( entity == null )
            return null;

        return StudentSimplified.builder()
                .id(entity.getId())
                .firstname(entity.getFirstName() )
                .lastname(entity.getLastName())
                .build();
    }
}
