package be.technofutur.Exercice_1.Presentation;

import be.technofutur.Exercice_1.Exceptions.ElementAlreadyExistsException;
import be.technofutur.Exercice_1.Exceptions.ElementNotFoundException;
import be.technofutur.Exercice_1.Service.CrudService;
import be.technofutur.Exercice_1.dto.IdentifiedDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

public abstract class AbstractCrudController<DTO extends IdentifiedDTO<ID>, ID>implements CrudController<DTO,ID> {

    protected final CrudService<DTO,ID> service;

    protected AbstractCrudController(CrudService<DTO, ID> service) {
        this.service = service;
    }


    @Override
    @GetMapping("/{id}")// GET - localhost:8080/?/{id}
    public ResponseEntity<DTO> getOne(@PathVariable ID id) throws ElementNotFoundException {

            //Va renvoyer une ResponseEntity avec un code OK si tout se passe bien
            return ResponseEntity
                    .ok(service.getOne(id));

    }

    @Override
    @GetMapping // GET - {domain}/?
    public ResponseEntity<List<DTO>> getAll() {
        return ResponseEntity.ok(service.getAll());
    }

    @Override
    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/update/{id}")
    public ResponseEntity<DTO> update(@Valid @RequestBody DTO dto,@PathVariable ID id) throws ElementNotFoundException {
            service.update(id,dto);
            return ResponseEntity.ok(service.getOne(id));
            //return ResponseEntity.ok().build();
    }

    @Override
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<DTO> delete(ID id) throws ElementNotFoundException {

            DTO dto= service.getOne(id);
            service.delete(id);
            return ResponseEntity.ok(dto);

    }

    @Override
    // va vérifier avant si la perspnne à le rôle admin
    @PreAuthorize("hasRole('ADMIN')")
    //@PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping
    public ResponseEntity<DTO> create(@Valid @RequestBody DTO dto) throws ElementAlreadyExistsException, ElementNotFoundException {

            service.addOne(dto);
            return ResponseEntity.ok( service.getOne(dto.getId()) );

    }
}
