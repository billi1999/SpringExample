package be.technofutur.Exercice_1.Security;

import be.technofutur.Exercice_1.Service.UserServiceImpl;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
//va surcharger la sécurité de base de Spring par cette classe-ci
@EnableWebSecurity
// va permettre de la sécurité sur les méthodes mêmes (controller, endpoint ou service)
// utili
@EnableGlobalMethodSecurity(
        //utilisation des annotations type PRE/POSTAuthorize (SPEL expression)
        // celui qu'on va utiliser
        prePostEnabled = true
        // Utiliser l'annotation @Secured
        //securedEnabled = true,
        //Utilisation de l'annotation @RoleAllowed
        //jsr250Enabled = true
)
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    private final PasswordEncoder passwordEncoder;
    private final UserServiceImpl userService;

    public SecurityConfig(PasswordEncoder passwordEncoder, UserServiceImpl userService) {
        this.passwordEncoder = passwordEncoder;
        this.userService = userService;
    }

    // Sert à charger les utilisateurs dans l'application
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {

        //Chargement en mémoire de l'app des utilisateurs hardcodés
        auth
                .inMemoryAuthentication()
                .withUser("jules")
                .password(passwordEncoder.encode("1234"))
                .roles("USER")
                .and()
                .withUser("admin")
                .password(passwordEncoder.encode("1234"))
                .roles("ADMIN","USER");
        /*
            Chargement en db des utilisateurs
         */
        //auth.userDetailsService(userService).passwordEncoder(passwordEncoder);
    }
    // Sert à configurer les routes sécurisées
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        /*
        Chargement en mémoire de l'app des utilisateurs hardcodés
         */

        http
                .csrf().disable()
                .httpBasic()
                .and()
                .authorizeRequests()
                .antMatchers(HttpMethod.GET,"/**").hasAnyRole("USER","ADMIN","_")
                .antMatchers(HttpMethod.POST,"/**/update").hasRole("ADMIN")
                .antMatchers(HttpMethod.PUT,"/**/update").hasRole("ADMIN")
                .and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS);

        //region exemple Grégory
        /*http
                // passer des données dans un formulaire
                // même si les bonnes données sont envoyées mais du mauvais endroit
                // est interdit
                .csrf().disable()
                // permet de dire via quel type de connexion on se connecte
                .httpBasic()
                .and()
                // à qui on autorise les requêtes
                .authorizeRequests()
                // Toute les routes commencent par h2-console et ses enfants
                // vont matcher avec cette configuration de sécurité

                // Tjs les + spécialisées avant les + générales
                .antMatchers("/h2-console/**").permitAll()
                //.antMatchers("/student").hasAnyRole("USER","ADMIN")
                .antMatchers("/student").hasAnyRole("USER","ADMIN")
                .antMatchers(HttpMethod.POST,"/users").permitAll()

                // Pour toute les autres, acceptes toute les requêtes
                // tant que l'utilisateur est authentifié
                .anyRequest().authenticated()
                .and()
                .sessionManagement()
                // pas de gestion de session dans le backend
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS);*/

                // Config spécial à H2
                /*
                .and()
                .headers().frameOptions().disable();*/
        //endregion

        //region Pour  Faire fonctionner sans problèmes de sécurité


        /*http
                .csrf().disable()
                .httpBasic()
                .and()
                .authorizeRequests()
                .anyRequest().permitAll()
                .and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        http.headers().frameOptions().sameOrigin();*/
        //endregion



    }
}
