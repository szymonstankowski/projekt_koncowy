package pl.szymonstankowski.user;

import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import pl.szymonstankowski.userPlants.UserPlants;

import javax.persistence.*;
import javax.validation.constraints.Email;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

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
    private String role;
    private Boolean enabled = false;
    private Boolean locked = false;

    @OneToMany
    List<UserPlants> userPlants;

    //TODO MM: W jakim celu trzymasz ten konstruktor?
    public User(String admin, String admin1, List<GrantedAuthority> role_admin) {
    }

    public User(String name, @Email String email, String password, String role) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.role = role;
    }

    public User() {
    }

    //TODO MM: UserDetails raczej implementujesz na serwisie niż na encji, bo encja nie musi nic wiedzieć o security.
    //TODO MM: Dziwnie te metodki wyglądają jedynie.
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return  Collections.singleton(new SimpleGrantedAuthority(role));
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
