package be.technofutur.Exercice_1.Presentation;

import be.technofutur.Exercice_1.Exceptions.InvalidPagedNbrException;
import be.technofutur.Exercice_1.Exceptions.PageSizeInvalidException;
import be.technofutur.Exercice_1.Service.CrudService;
import be.technofutur.Exercice_1.Service.ProfessorService;
import be.technofutur.Exercice_1.Service.ProfessorServiceImpl;
import be.technofutur.Exercice_1.dto.ProfessorDTO;
import be.technofutur.Exercice_1.dto.container.PagedContainer;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/professor")
public class ProfessorController extends AbstractCrudController<ProfessorDTO,Integer> {
    public ProfessorController(ProfessorService service) {
        super(service);
    }

    @GetMapping("/paged/{pageNbr}")
    @ResponseStatus(HttpStatus.OK)
    public PagedContainer<ProfessorDTO> getPaged(@PathVariable("id") int pageNbr, @RequestParam(value = "size",required = false) Integer nbrElements) throws PageSizeInvalidException, InvalidPagedNbrException {
        if (nbrElements == null){
            nbrElements = 2;
        }
        if (nbrElements!= 2 && nbrElements != 3)
            throw new PageSizeInvalidException("2 | 3");
        //
        Page<ProfessorDTO> page = ((ProfessorService)service).getPage(pageNbr,nbrElements);

        if (page.isEmpty()){
            throw new InvalidPagedNbrException(pageNbr,page.getTotalPages());
        }
        PagedContainer<ProfessorDTO> container = new PagedContainer<>(
                page.getContent(),
                page.getTotalPages(),
                page.getNumberOfElements(),
                page.getNumber(),
                page.getTotalElements()
        );
        if (page.hasNext()){
            container.setNextPage("/prof/paged/"+page.nextPageable().getPageNumber());
        }
        if (page.hasPrevious()){
            container.setPreviousPage("prof/paged/"+page.previousPageable().getPageNumber());
        }
        return container;
   // Page<ProfessorDTO> page= (ProfessorServiceImpl)service.get
    }
}
