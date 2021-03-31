package be.technofutur.Exercice_1.Mapper;

import be.technofutur.Exercice_1.Entities.Section;
import be.technofutur.Exercice_1.dto.SectionSimplified;
import org.springframework.stereotype.Component;

@Component
public class SectionSimplifiedMapper {
    SectionSimplified toDTO(Section entity){
        if( entity == null )
            return null;

        return SectionSimplified.builder()
                .id(entity.getId())
                .name(entity.getSection_name())
                .build();
    }
}
