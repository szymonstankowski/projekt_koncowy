package pl.szymonstankowski.floraManager.user;

public class UserService {

    private final  UserRepository userRepository;


    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    };

    public void saveUser(User user){
        userRepository.save(user);
    }
}
