package be.technofutur.Exercice_1.Mapper;

public interface Mapper<DTO,ENTITY> {
    DTO toDTO(ENTITY entity);
    ENTITY toEntity(DTO dto);

}
