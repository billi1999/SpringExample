package be.technofutur.Exercice_1.Service;

import be.technofutur.Exercice_1.Entities.User;
import be.technofutur.Exercice_1.dto.UserDTO;

import java.util.List;

public interface UserService{
    List<UserDTO> getAll();
    boolean insert(User user);
}
