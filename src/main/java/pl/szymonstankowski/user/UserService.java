package pl.szymonstankowski.user;

import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.szymonstankowski.registration.token.ConfirmationToken;
import pl.szymonstankowski.registration.token.ConfirmationTokenService;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final ConfirmationTokenService confirmationTokenService;
    private final static String USER_NOT_FOUND_MSG = "User %s not found";

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder, ConfirmationTokenService confirmationTokenService) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.confirmationTokenService = confirmationTokenService;
    }

    public void saveUser(User user){
        boolean exists = userRepository.findUserByEmail(user.getEmail()).isPresent();

        if (exists){
            throw new IllegalStateException("email already taken");
        }

        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRole("ROLE_USER");

        userRepository.save(user);

        String token = UUID.randomUUID().toString();

        ConfirmationToken confirmationToken = new ConfirmationToken(
                token,
                LocalDateTime.now(),
                LocalDateTime.now().plusMinutes(15),
                user
        );
        confirmationTokenService.saveConfirmationToken(confirmationToken);

        //TODO: send email
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

    public void enableAppUser(String email) {
         userRepository.enableUser(email);
    }




}
