package be.technofutur.Exercice_1.Repository;

import be.technofutur.Exercice_1.Entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User,Integer> {
    //va executer la recherche en fonction du nom
    Optional<User> findByUsername(String username);
}
