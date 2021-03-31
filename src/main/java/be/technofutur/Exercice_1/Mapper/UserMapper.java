package be.technofutur.Exercice_1.Mapper;

import be.technofutur.Exercice_1.Entities.User;
import be.technofutur.Exercice_1.dto.UserDTO;
import org.springframework.stereotype.Component;


@Component
public class UserMapper implements Mapper<UserDTO, User>{
    @Override
    public UserDTO toDTO(User entity) {
        if(entity != null){
            return UserDTO.builder()
                    .id(entity.getId())
                    .password(entity.getPassword())
                    .roles(entity.getRoles())
                    .username(entity.getUsername())
                    .build();
        }else {
            return null;
        }

    }

    @Override
    public User toEntity(UserDTO dto) {
        if(dto != null){
            return User.builder()
                    .id(dto.getId())
                    .password(dto.getPassword())
                    .roles(dto.getRoles())
                    .username(dto.getUsername())
                    .build();
        }else {
            return null;
        }
    }
}
