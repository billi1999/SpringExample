package be.technofutur.Exercice_1.Service.Pagination;

import be.technofutur.Exercice_1.Entities.Student;
import be.technofutur.Exercice_1.Mapper.StudentMapper;
import be.technofutur.Exercice_1.Repository.StudentRepository;
import be.technofutur.Exercice_1.dto.StudentDTO;
import be.technofutur.Exercice_1.dto.container.PagedContainer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
public class DemoService {

    public final int ELEMENTS_BY_PAGE = 5;

    private final StudentRepository repo;
    private final StudentMapper mapper;

    public DemoService(StudentRepository repo, StudentMapper mapper) {
        this.repo = repo;
        this.mapper = mapper;
    }

    public Page<StudentDTO> getAllStudents( int page){

         Page<StudentDTO> list = repo.findAll(PageRequest.of(page,ELEMENTS_BY_PAGE))
                .map(mapper::toDTO);
         return list;


    }


}
