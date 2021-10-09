package pl.szymonstankowski;

import org.springframework.context.annotation.Configuration;
import pl.szymonstankowski.user.UserRepository;

@Configuration
public class Start {

    private UserRepository userRepository;

    public Start(UserRepository userRepository){
        this.userRepository = userRepository;
    }
}
