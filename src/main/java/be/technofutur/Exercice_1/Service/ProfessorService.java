package be.technofutur.Exercice_1.Service;

import be.technofutur.Exercice_1.Exceptions.PageSizeInvalidException;
import be.technofutur.Exercice_1.dto.ProfessorDTO;
import org.springframework.data.domain.Page;

public interface ProfessorService extends CrudService<ProfessorDTO,Integer>{

    Page<ProfessorDTO> getPage(int pageNumber,int size) throws PageSizeInvalidException;
}
