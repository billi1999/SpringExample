package be.technofutur.Exercice_1.Service;

import be.technofutur.Exercice_1.Entities.Professor;
import be.technofutur.Exercice_1.Exceptions.ElementAlreadyExistsException;
import be.technofutur.Exercice_1.Exceptions.ElementNotFoundException;
import be.technofutur.Exercice_1.Mapper.ProfessorMapper;
import be.technofutur.Exercice_1.Repository.ProfessorRepository;
import be.technofutur.Exercice_1.dto.ProfessorDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProfessorServiceImpl implements ProfessorService{
    private final ProfessorRepository repository;
    private final ProfessorMapper mapper;

    public ProfessorServiceImpl(ProfessorRepository repository, ProfessorMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public ProfessorDTO getOne(Integer integer) throws ElementNotFoundException {

        if(integer == null){
            throw new IllegalArgumentException();
        }
        if (!repository.existsById(integer)){
            throw new ElementNotFoundException(integer);
        }
        return mapper.toDTO(repository.getOne(integer));
    }

    @Override
    public List<ProfessorDTO> getAll() {

        return repository.findAll()
                .stream()
                .map(mapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public void addOne(ProfessorDTO professorDTO) throws ElementAlreadyExistsException {
        if (professorDTO == null){
            throw new IllegalArgumentException();
        }
        if (repository.existsById(professorDTO.getId())){
            throw new ElementAlreadyExistsException();
        }
        repository.save(mapper.toEntity(professorDTO));
    }

    @Override
    public void delete(Integer integer) throws ElementNotFoundException {
        if(integer == null){
            throw new IllegalArgumentException();
        }
        if(!repository.existsById(integer)){
            throw new ElementNotFoundException(integer);
        }
        repository.deleteById(integer);
    }

    @Override
    public void update(Integer integer, ProfessorDTO professorDTO) throws ElementNotFoundException {
        if (integer == null || professorDTO == null){
            throw new IllegalArgumentException();
        }
        if (!repository.existsById(integer)){
            throw new ElementNotFoundException(integer);
        }
        /*
        Professor professor=repository.getOne(integer);
        professor.setHireDate(professorDTO.getHireDate());
        professor.setMail(professorDTO.getMail());
        professor.setName(professorDTO.getName());
        professor.setOffice(professorDTO.getOffice());
        //professor.set
        //professor.setSection(professorDTO.getSection());
        repository.save(professor);*/
        Professor p =mapper.toEntity(professorDTO);

        Professor prof= repository.findById(integer)
                .orElseThrow(()-> new ElementNotFoundException(integer));
        prof.setHireDate(p.getHireDate());
        prof.setMail(p.getMail());
        prof.setName(p.getName());
        prof.setOffice(p.getOffice());
        prof.setSection(p.getSection());
        prof.setWage(p.getWage());
        prof.setSurname(p.getSurname());

        repository.save(prof);

    }
    /*public Page<ProfessorDTO> getPagedProfessor(int page, Integer elements_by_page) throws IllegalElementNumberException {
        if (elements_by_page ==null){
            return repository.findAll(PageRequest.of(page,2)).map(mapper::toDTO);
        }else if (elements_by_page != 2 && elements_by_page!=3){
            throw new IllegalElementNumberException();
        }
        else
            return repository.findAll(PageRequest.of(page,elements_by_page)).map(mapper::toDTO);

    }
*/
    @Override
    public Page<ProfessorDTO> getPage(int pageNumber, int size) {
        return repository.findAll(PageRequest.of(pageNumber, size)).map(mapper::toDTO);
    }
}
