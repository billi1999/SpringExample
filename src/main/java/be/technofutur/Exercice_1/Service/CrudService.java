package be.technofutur.Exercice_1.Service;

import be.technofutur.Exercice_1.Entities.Section;
import be.technofutur.Exercice_1.Exceptions.ElementAlreadyExistsException;
import be.technofutur.Exercice_1.Exceptions.ElementNotFoundException;
import be.technofutur.Exercice_1.dto.SectionDTO;

import java.util.List;

public interface CrudService<DTO, ID> {

    //READ
    DTO getOne(ID id) throws ElementNotFoundException;
    List<DTO> getAll();
    //INSERT
    void addOne(DTO dto) throws ElementAlreadyExistsException;
    //DELETE
    void delete(ID id) throws ElementNotFoundException;
    //UPDATE
    void update(ID id,DTO dto) throws ElementNotFoundException;



}
