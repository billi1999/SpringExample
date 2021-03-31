package be.technofutur.Exercice_1.Entities;

import lombok.*;
import lombok.experimental.FieldDefaults;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Entity
@Table(name = "demo_user")
@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Integer id;

    @Column(nullable = false,unique = true)
    String username;

    @Column(nullable = false)
    String password;

    Boolean accountNonExpired;

    Boolean accountNonLocked;

    Boolean credentialNonExpired;

    Boolean enable;

    // va Tout le temps aller rechercher dans la table tout le temps les données
    // alors qu'en lazy, ne le charge que sur demande( A VÉRIFIER )
    @ElementCollection(fetch = FetchType.EAGER)
    //Génère des associations Many-Many pour un seul Élément
    List<String> roles = new ArrayList<>();
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return roles.stream()
                //va transformer les String en collections de rôles
                .map(r->new SimpleGrantedAuthority(r))
                .collect(Collectors.toList());
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return accountNonExpired;
    }

    @Override
    public boolean isAccountNonLocked() {
        return accountNonLocked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return credentialNonExpired;
    }

    @Override
    public boolean isEnabled() {
        return enable;
    }
}
