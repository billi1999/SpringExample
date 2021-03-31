package be.technofutur.Exercice_1.Repository;

import be.technofutur.Exercice_1.Entities.Professor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProfessorRepository extends JpaRepository<Professor,Integer> {
}
