package be.technofutur.Exercice_1.Presentation;

import be.technofutur.Exercice_1.Exceptions.ElementAlreadyExistsException;
import be.technofutur.Exercice_1.Exceptions.ElementNotFoundException;
import be.technofutur.Exercice_1.Service.CrudService;
import be.technofutur.Exercice_1.Service.SectionService;
import be.technofutur.Exercice_1.dto.SectionDTO;
import org.springframework.context.annotation.Profile;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/section")
@Profile("api")
public class SectionController extends AbstractCrudController<SectionDTO,Integer>{
    private final SectionService service;

    public SectionController(CrudService<SectionDTO, Integer> service, SectionService service1) {
        super(service);
        this.service = service1;
    }
    /*
    @Override
    @PostMapping(path = "/add")
    //Encapsule une réponse http
    public ResponseEntity<SectionDTO> create(@RequestBody SectionDTO sectionDTO)  {
        try {

            // on va rajouter des headers à la requête
            // n'a aucun intérét dans ce cas-ci
            // Permet d'associer une clé à plusieurs valeurs
            HttpHeaders headers = new HttpHeaders();
            headers.add("fromController","SectionController");
            headers.add("fromController","SectionController2");
            service.addOne(sectionDTO);
            return new ResponseEntity<SectionDTO>(sectionDTO,HttpStatus.CREATED);
        }catch (ElementAlreadyExistsException e){
            return ResponseEntity
                    .badRequest()
                    .header("fromController","SectionController","SectionController2")
                    .build();
        }
    }


    @Override
    @GetMapping(path = "/{id}")
    public ResponseEntity<SectionDTO> getOne(@PathVariable(value = "id") Integer integer) {
        try {
            //Va renvoyer une ResponseEntity avec un code OK si tout se passe bien
            return ResponseEntity
                    .ok(service.getOne(integer));
        } catch (ElementNotFoundException e) {
            return ResponseEntity
                    .status(404)
                    .build();
        }
    }

    @Override
    @GetMapping(path = "/all")
    public ResponseEntity<List<SectionDTO>> getAll() {
        return ResponseEntity.ok(service.getAll());
    }

    @Override
    @PutMapping(path="/update/{id}")
    public ResponseEntity<SectionDTO> update(@RequestBody SectionDTO sectionDTO, @PathVariable("id") Integer integer) {
        try {
            service.update(integer,sectionDTO);
            return ResponseEntity.ok(service.getOne(integer));
        }catch (ElementNotFoundException e){
            return ResponseEntity.badRequest().build();
        }

    }*/
    //Première manière
   /* @Override
    @GetMapping(path = "/delete/{id}")
    public ResponseEntity<SectionDTO> delete(@PathVariable("id") Integer integer) {
        try {
            service.delete(integer);
            return ResponseEntity.ok().build();
        }catch (ElementNotFoundException e){
            return ResponseEntity.notFound().build();
        }
    }*/
    //deuxième manière

/*
    @Override
    @DeleteMapping("/{id}/delete")
    public ResponseEntity<SectionDTO> delete(@PathVariable("id") Integer integer) {
        SectionDTO dto = null;
        try {
            dto = service.getOne(integer);
            service.delete(integer);
            return ResponseEntity.ok(dto);
        } catch (ElementNotFoundException e) {
            return ResponseEntity.status(404).build();
        }
}*/




    @GetMapping("/test")
    public ResponseEntity<String> test(){
        return null;
    }

}
