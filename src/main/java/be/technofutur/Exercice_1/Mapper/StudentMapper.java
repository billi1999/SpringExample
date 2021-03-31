package be.technofutur.Exercice_1.Mapper;

import be.technofutur.Exercice_1.Entities.Student;
import be.technofutur.Exercice_1.Repository.SectionRepository;
import be.technofutur.Exercice_1.dto.StudentDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class StudentMapper implements Mapper<StudentDTO, Student>{
    @Autowired
    private SectionSimplifiedMapper SectionMapper;
    @Autowired
    private SectionRepository repo;
    /*
    public StudentMapper(Mapper<SectionDTO, Section> sectionMapper) {
        SectionMapper = sectionMapper;
    }*/

    @Override
    public StudentDTO toDTO(Student student) {
        if (student == null){
            //throw new IllegalArgumentException();
            return null;
        }
        return StudentDTO.builder()
                .id(student.getId())
                .firstName(student.getFirstName())
                .lastName(student.getLastName())
                .birthDate(student.getBirthDate())
                .login(student.getLogin())
                .section(SectionMapper.toDTO(student.getSection()))
                .yearResult(student.getYearResult())
                .courseId(student.getCourseId())
                .build();
    }

    @Override
    public Student toEntity(StudentDTO studentDTO) {
        if (studentDTO == null){
            return null;
        }
        return Student.builder()
                .id(studentDTO.getId())
                .firstName(studentDTO.getFirstName())
                .lastName(studentDTO.getLastName())
                .birthDate(studentDTO.getBirthDate())
                .login(studentDTO.getLogin())
                .section(repo.getOne(studentDTO.getSection().getId()))
                .yearResult(studentDTO.getYearResult())
                .courseId(studentDTO.getCourseId())
                .build();
    }
}
