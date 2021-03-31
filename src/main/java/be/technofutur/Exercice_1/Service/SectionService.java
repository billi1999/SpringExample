package be.technofutur.Exercice_1.Service;

import be.technofutur.Exercice_1.Entities.Section;
import be.technofutur.Exercice_1.Entities.Student;
import be.technofutur.Exercice_1.Exceptions.ElementAlreadyExistsException;
import be.technofutur.Exercice_1.Exceptions.ElementNotFoundException;
import be.technofutur.Exercice_1.Mapper.Mapper;
import be.technofutur.Exercice_1.Mapper.SectionMapper;
import be.technofutur.Exercice_1.Repository.SectionRepository;
import be.technofutur.Exercice_1.dto.SectionDTO;
import be.technofutur.Exercice_1.dto.StudentDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;


@Service
public class SectionService implements CrudService<SectionDTO,Integer> {
    private final SectionRepository repo;
    private final Mapper<SectionDTO,Section> mapper;
    private final Mapper<StudentDTO, Student> studentMapper;

    public SectionService(SectionRepository repo, SectionMapper mapper, Mapper<StudentDTO, Student> studentMapper) {
        this.repo = repo;
        this.mapper = mapper;
        this.studentMapper = studentMapper;
    }

    @Override
    @Transactional
    public SectionDTO getOne(Integer integer) throws ElementNotFoundException {
        /*Section entity=null;
        try {
            entity=repo.findById(integer).get();
        }catch (NoSuchElementException e){
            throw new ElementNotFoundException(integer);
        }
        return mapper.toDTO(entity);*/
        if(integer == null){
            throw new IllegalArgumentException();
        }
        if (!repo.existsById(integer)){
            throw new ElementNotFoundException(integer);
        }
        return mapper.toDTO(repo.getOne(integer));

    }

    @Override
    public List<SectionDTO> getAll() {
        return repo.findAll()
                .stream()
                .map(mapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public void addOne(SectionDTO sectionDTO) throws ElementAlreadyExistsException {
        if (sectionDTO == null){
            throw new IllegalArgumentException();
        }
        if(repo.existsById(sectionDTO.getId())){
            throw new ElementAlreadyExistsException();
        }
        repo.save(mapper.toEntity(sectionDTO));
    }

    @Override
    public void delete(Integer integer) throws ElementNotFoundException {
        if (integer == null){
            throw new IllegalArgumentException();
        }
        if(!repo.existsById(integer)){
            throw new ElementNotFoundException(integer);
        }

        repo.deleteById(integer);
    }

    @Override
    @Transactional
    public void update(Integer integer, SectionDTO sectionDTO) throws ElementNotFoundException {
        if (integer == null || sectionDTO == null){
            throw new IllegalArgumentException();
        }
        if(!repo.existsById(integer)){
            throw new ElementNotFoundException(integer);
        }
        Section section = repo.getOne(integer);
        //section.setSection_id(sectionDTO.getSection_id());
        section.setSection_name(sectionDTO.getSection_name());
        section.setDelegate_id(sectionDTO.getDelegate_id());
    }

    public List<StudentDTO> getStudentBySectionId(Integer section_id) throws ElementNotFoundException {
        if (section_id == null){
            throw new IllegalArgumentException();
        }
        if (!repo.existsById(section_id)){
            throw new ElementNotFoundException(section_id);
        }
        Section section = repo.getOne(section_id);
        return section.getStudents().stream()
                .map(studentMapper::toDTO)
                .collect(Collectors.toList());
    }
   /* @Override
    @Transactional
    public SectionDTO getOne(int id) {

        return mapper.toDTO(repo.getOne(id));
    }

    @Override
    public List<SectionDTO> getAll() {
        return repo.findAll()
                .stream()
                .map(mapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public void addOne(SectionDTO section) {
        repo.save(mapper.toEntity(section));
    }

    @Override
    public void delete(int id) {
        Section s=repo.getOne(id);
        repo.delete(s);
    }

    @Override
    @Transactional
    public SectionDTO update(int id, SectionDTO sectionDTO) {
        Section s=repo.getOne(id);
        s.setSection_id(sectionDTO.getSection_id());
        s.setDelegate_id(sectionDTO.getDelegate_id());
        s.setSection_name(sectionDTO.getSection_name());
        //repo.save(s);
        return mapper.toDTO(s);
    }*/

    /*@GetMapping("/students/sections/{id}")
    public ResponseEntity<List<StudentDTO>> getStudentFromSection(@PathVariable("id") Integer id){
        repo.

    }*/
}
