package be.technofutur.Exercice_1.Presentation;

import be.technofutur.Exercice_1.Entities.User;
import be.technofutur.Exercice_1.Service.UserServiceImpl;
import be.technofutur.Exercice_1.dto.UserDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/users")
public class UserController {

    private final UserServiceImpl userService;

    public UserController(UserServiceImpl userService) {
        this.userService = userService;
    }

    @GetMapping
    public ResponseEntity<List<UserDTO>> getAllUsers(){
        return ResponseEntity.ok(this.userService.getAll());
    }

    @PostMapping
    public ResponseEntity<Boolean> insertUser(@RequestBody User user){
        return this.userService.insert(user) ?
                ResponseEntity.ok(true) :
                ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(false);
    }
}
