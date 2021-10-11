package pl.szymonstankowski.user;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public void saveUser(User user){
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRole("USER");
        userRepository.save(user);
    }
    @Transactional
    public void deleteUser(String name){
        userRepository.deleteUserByName(name);
    }

    public User getUserByName(String username){
        return userRepository.findUserByName(username).orElse(null);
    }

    public User getUserByEmail(String email){
        return userRepository.findUserByName(email).orElse(null);
    }

    public User getUserById(Long id){
        return userRepository.findUserById(id);
    }

}
