package pl.szymonstankowski.registration;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import pl.szymonstankowski.user.User;
import pl.szymonstankowski.user.UserService;

import javax.validation.Valid;

@Controller

public class RegistrationController {

    private final UserService userService;



    public RegistrationController(UserService userService) {
        this.userService = userService;

    }


    @GetMapping("/registration")
    public String registrationForm (Model model){
        model.addAttribute("user", new User());
        return "user-form";
    }
    @PostMapping("/registerNewUser")
    public String register (@Valid User user, BindingResult result, Model model){
        if (result.hasErrors()){
            return "user-form";
        }else {

            userService.saveUser(user);
            model.addAttribute("user", user);
            return "redirect:/login";
        }
    }


}
