package pl.szymonstankowski;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import pl.szymonstankowski.user.User;
import pl.szymonstankowski.user.UserRepository;

import javax.annotation.PostConstruct;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service

public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserRepository userRepository;
    private final static String USER_NOT_FOUND_MSG ="User %s not found!";

    public UserDetailsServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findUserByName(username)
               .orElseThrow(()-> new UsernameNotFoundException(String.format(USER_NOT_FOUND_MSG,username)));
    }

    private Map<String, User> roles = new HashMap<>();

    private List<GrantedAuthority> getAuthority(String role) {
        return Collections.singletonList(new SimpleGrantedAuthority(role));
    }

    @PostConstruct
    public void init() {
        roles.put("admin2", new User("admin", "admin1", getAuthority("ROLE_ADMIN")));
        roles.put("user2", new User("user", "user1", getAuthority("ROLE_USER")));
    }
}
