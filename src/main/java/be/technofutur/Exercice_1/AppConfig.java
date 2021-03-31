package be.technofutur.Exercice_1;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Scanner;

@Configuration
public class AppConfig {

    @Bean
    public Scanner scanner(){
        return new Scanner(System.in);
    }

    // va encoder les mots de passes
    // pour ne pas les enregistrer tel quel dans la db
    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
}
