package be.technofutur.Exercice_1.Service;

import be.technofutur.Exercice_1.Entities.User;
import be.technofutur.Exercice_1.Mapper.UserMapper;
import be.technofutur.Exercice_1.Repository.UserRepository;
import be.technofutur.Exercice_1.dto.UserDTO;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService, UserDetailsService {

    private final UserRepository userRepository;

    private final UserMapper mapper;

    private final PasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepository userRepository, UserMapper mapper, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.mapper = mapper;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public List<UserDTO> getAll() {
        return this.userRepository.findAll()
                .stream().map(mapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public boolean insert(User user) {
        User uInserted = null;
        if (user!= null){
            // on est obligé de le mettre true ici
            // car dans entities n
            user.setAccountNonExpired(true);
            user.setAccountNonLocked(true);
            user.setCredentialNonExpired(true);
            user.setEnable(true);
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            uInserted = this.userRepository.save(user);
        }
        return uInserted != null && this.userRepository.existsById(uInserted.getId());
    }

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        return userRepository
                .findByUsername(s)
                .orElseThrow(()->new UsernameNotFoundException("User with username : "+s+""));
    }
}
