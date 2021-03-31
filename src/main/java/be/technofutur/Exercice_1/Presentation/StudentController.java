package be.technofutur.Exercice_1.Presentation;

import be.technofutur.Exercice_1.Exceptions.ElementAlreadyExistsException;
import be.technofutur.Exercice_1.Exceptions.ElementNotFoundException;
import be.technofutur.Exercice_1.Service.CrudService;
import be.technofutur.Exercice_1.dto.StudentDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
//ResponseBody est présent dans RestController
// et pas dans le @Controller de base
//Va permettre de ne plus utiliser Response Entity ( voir plus tard )
@RequestMapping(path = "/student")
public class StudentController extends AbstractCrudController<StudentDTO, Integer> {
   // private final CrudService<StudentDTO,Integer> service;

    protected StudentController(CrudService<StudentDTO, Integer> service) {
        super(service);
    }

    /*
    @Override
    @PostMapping(path = "/add")
    public ResponseEntity<StudentDTO> create(@RequestBody StudentDTO studentDTO) {
        try {
            service.addOne(studentDTO);
            return ResponseEntity.ok(service.getOne(studentDTO.getId()));
            //return ResponseEntity.ok().build();
        }catch (ElementAlreadyExistsException | ElementNotFoundException e){
            return ResponseEntity.badRequest().build();
        }
    }

    @Override
    @GetMapping(path = "/{id}")
    public ResponseEntity<StudentDTO> getOne(@PathVariable(value = "id") Integer integer) {
        try {
            return ResponseEntity.ok(service.getOne(integer));
        }catch (ElementNotFoundException e){
            //return ResponseEntity.notFound().build();
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }



    @Override
    @GetMapping(path = "/all")
    public ResponseEntity<List<StudentDTO>> getAll() {
        return ResponseEntity.ok(service.getAll());
    }


    @Override
    @PutMapping(path = "/update/{id}")
    public ResponseEntity<StudentDTO> update(@RequestBody StudentDTO studentDTO, @PathVariable("id") Integer integer) {
        try {
            service.update(integer,studentDTO);
            return ResponseEntity.ok(service.getOne(integer));
            //return ResponseEntity.ok().build();
        }catch (ElementNotFoundException e){
            return ResponseEntity.notFound().build();
        }
    }

    @Override
    @GetMapping(path = "/delete/{id}")
    public ResponseEntity<StudentDTO> delete(@PathVariable("id") Integer integer) {
        try{
            //StudentDTO dto= service.getOne(integer);
            service.delete(integer);
            return ResponseEntity.ok().build();
        }catch (ElementNotFoundException e){
            return ResponseEntity.notFound().build();
        }
    }
    */

}
