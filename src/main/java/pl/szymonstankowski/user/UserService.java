package pl.szymonstankowski.user;

import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void saveUser(User user){
        userRepository.save(user);
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
