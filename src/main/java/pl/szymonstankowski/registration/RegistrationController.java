package pl.szymonstankowski.registration;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import pl.szymonstankowski.user.User;
import pl.szymonstankowski.user.UserService;

import javax.validation.Valid;

@Controller

public class RegistrationController {

    private final UserService userService;
    private final RegistrationService registrationService;



    public RegistrationController(UserService userService, RegistrationService registrationService) {
        this.userService = userService;

        this.registrationService = registrationService;
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
            model.addAttribute("user", user);
          //  return "Sprawdz swojego poczte i potwierdz email!";
            return registrationService.register(user);
        }
    }

    @GetMapping("/confirm?token=")
    public String confirm(@RequestParam("token") String token){
        registrationService.confirmToken(token);
        return "redirect:/login";
    }


}
