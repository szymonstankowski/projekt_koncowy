package pl.szymonstankowski.user;

import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final static String USER_NOT_FOUND_MSG = "User %s not found";

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public void saveUser(User user){
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRole("ROLE_USER");
        userRepository.save(user);
    }

    public User getUserByLogin(String login){

        return userRepository.findUserByName(login)
                .orElseThrow(()-> new UsernameNotFoundException(String.format(USER_NOT_FOUND_MSG, login)));
    }

    @Transactional
    public void deleteUserById(Long id){
        userRepository.deleteById(id);
    }

    public User getUserByName(String username){
        return userRepository.findUserByName(username)
                .orElseThrow(()-> new UsernameNotFoundException(String.format(USER_NOT_FOUND_MSG,username)));
    }

    public User getUserByEmail(String email){
        return userRepository.findUserByName(email)
                .orElseThrow(()-> new UsernameNotFoundException(String.format(USER_NOT_FOUND_MSG,email)));
    }

    public User getUserById(Long id){
        return userRepository.findUserById(id);
    }

    public List<User> getUsers(){
        return userRepository.findAll();
    }

    public String signUp(User user){
        return "";
    }

}
