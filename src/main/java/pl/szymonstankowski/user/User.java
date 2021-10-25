package pl.szymonstankowski.user;

import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import pl.szymonstankowski.userPlants.UserPlants;

import javax.persistence.*;
import javax.validation.constraints.Email;
import java.util.*;

@Entity
@Data
public class User implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String name;
    @Email
    @Column(unique = true)
    private String email;
    private String password;
    private Map<String,String> role;
    private Boolean enabled = false;
    private Boolean locked = false;

    @OneToMany
    List<UserPlants> userPlants;

    public User(String admin, String admin1, List<GrantedAuthority> role_admin) {
    }

    public User(String name, @Email String email, String password, Map<String,String> role) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.role=role;
    }

    public User() {
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return  Collections.singleton(new SimpleGrantedAuthority(role.get(role)));
    }

    @Override
    public String getUsername() {
        return name;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return enabled;
    }

    public String getPassword(){
        return password;
    }
}
