package be.technofutur.Exercice_1.Presentation;

import be.technofutur.Exercice_1.Entities.Section;
import be.technofutur.Exercice_1.Exceptions.ElementAlreadyExistsException;
import be.technofutur.Exercice_1.Exceptions.ElementNotFoundException;
import be.technofutur.Exercice_1.dto.SectionDTO;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface CrudController<DTO, ID>{
    // Create
    ResponseEntity<DTO> create(DTO dto) throws ElementAlreadyExistsException, ElementNotFoundException;
    // Read
    ResponseEntity<DTO> getOne(ID id) throws ElementNotFoundException;
    //List<DTO> getAll();
    ResponseEntity<List<DTO>> getAll();


    // Update
    //DTO update(DTO dto,ID id);
    ResponseEntity<DTO> update(DTO dto,ID id) throws ElementNotFoundException;

    // Delete
    //DTO delete(ID id);
    ResponseEntity<DTO> delete(ID id) throws ElementNotFoundException;

}
