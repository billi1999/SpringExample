package be.technofutur.Exercice_1.Presentation;

import be.technofutur.Exercice_1.Service.Pagination.DemoService;
import be.technofutur.Exercice_1.dto.StudentDTO;
import be.technofutur.Exercice_1.dto.container.PagedContainer;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.stream.Collectors;

@RestController
@RequestMapping("/demo")
public class DemoController {
    private final DemoService service;

    public DemoController(DemoService service) {
        this.service = service;
    }

    @GetMapping(path = "/students/{id}")
    public ResponseEntity<PagedContainer<StudentDTO>> getPaged(@PathVariable("id") int pageNbr){

        Page<StudentDTO> page = service.getAllStudents(pageNbr);



        PagedContainer<StudentDTO> container = new PagedContainer<>(
                page.stream().collect(Collectors.toList()),
                page.getTotalPages(),
                page.getNumberOfElements(),
                page.getNumber(), page.getTotalElements());
        if (page.hasNext()){
            container.setNextPage("http://localhost:9001/demo/student"+page.previousPageable().getPageNumber());
        }
        if (page.hasPrevious()){
            container.setPreviousPage("http://localhost:9001/demo/student"+page.nextPageable().getPageNumber());

        }
        return ResponseEntity.ok(container);
    }
}
