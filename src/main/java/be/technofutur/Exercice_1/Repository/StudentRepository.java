package be.technofutur.Exercice_1.Repository;

import be.technofutur.Exercice_1.Entities.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student,Integer> {
}
