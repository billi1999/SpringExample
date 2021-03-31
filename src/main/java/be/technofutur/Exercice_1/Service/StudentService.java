package be.technofutur.Exercice_1.Service;

import be.technofutur.Exercice_1.Entities.Student;
import be.technofutur.Exercice_1.Exceptions.ElementAlreadyExistsException;
import be.technofutur.Exercice_1.Exceptions.ElementNotFoundException;
import be.technofutur.Exercice_1.Mapper.Mapper;
import be.technofutur.Exercice_1.Repository.StudentRepository;
import be.technofutur.Exercice_1.dto.StudentDTO;
import org.springframework.stereotype.Service;
import org.springframework.util.AutoPopulatingList;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class StudentService implements CrudService<StudentDTO,Integer>{
    private final Mapper<StudentDTO, Student> mapper;
    private final StudentRepository repository;

    public StudentService(Mapper<StudentDTO, Student> mapper, StudentRepository repository) {
        this.mapper = mapper;
        this.repository = repository;
    }


    @Override
    public StudentDTO getOne(Integer integer) throws ElementNotFoundException {
        if (integer == null){
            throw new IllegalArgumentException();
        }
        /* Autre manière de vérifier sir l'élément existe
        if (!repository.existsById(integer)){
            throw new ElementNotFoundException(integer);

        }*/
        Student entity = repository.findById(integer).orElseThrow(()->new ElementNotFoundException(integer));
        return mapper.toDTO(entity);

    }

    @Override
    public List<StudentDTO> getAll() {
        return repository.findAll()
                .stream()
                .map(mapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public void addOne(StudentDTO studentDTO) throws ElementAlreadyExistsException {
        if (studentDTO==null){
            throw new IllegalArgumentException();
        }
        if (repository.existsById(studentDTO.getId())){
            throw new ElementAlreadyExistsException();
        }
        repository.save(mapper.toEntity(studentDTO));

    }

    @Override
    public void delete(Integer id) throws ElementNotFoundException {
        if (id == null){
            throw new IllegalArgumentException();
        }
        if (!repository.existsById(id)){
            throw new ElementNotFoundException(id);
        }
        repository.deleteById(id);

    }

    @Override
    @Transactional
    public void update(Integer id, StudentDTO studentDTO) throws ElementNotFoundException {
        if (id == null || studentDTO== null){
            throw new IllegalArgumentException();
        }
        /*
        if(!repository.existsById(id)){
            throw new ElementNotFoundException(id);
        }
        Student student = repository.getOne(id);*/
        Student student = repository.findById(id).orElseThrow(()->new ElementNotFoundException(id));

        student.setBirthDate(studentDTO.getBirthDate());
        student.setCourseId(studentDTO.getCourseId());
        student.setFirstName(studentDTO.getFirstName());
        student.setLastName(student.getLastName());
        student.setLogin(student.getLogin());
        student.setYearResult(student.getYearResult());
        //student.setSectionId(student.getSectionId());
        repository.save(student);


    }
}
