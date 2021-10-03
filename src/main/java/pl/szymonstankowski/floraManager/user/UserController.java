package pl.szymonstankowski.floraManager.user;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/users")
    public String showUsers(Model model) {
        List<User> userList = userService.showUsers();
        model.addAttribute("users", userList);
        return "user-list";
    }

    @GetMapping("/newUser")
    public String createUser(Model model) {
        model.addAttribute("user", new User());
        return "user-form";
    }

    @PostMapping("/newUser")
    public String saveUser(@Valid User user, BindingResult result) {
        if (result.hasErrors()) {
            return "user-form";
        } else {
            userService.saveUser(user);
            return "redirect:/userPage/" + user.getId();
        }
    }

    @GetMapping("/userPage/{id}")
    public String userPage(@PathVariable Long id, Model model) {
        User user1 = userService.showUser(id);
        model.addAttribute("user", user1);
        return "user-page";
    }


    @GetMapping("/userEdit/{id}")
    public String editUser(@PathVariable Long id, Model model) {
        model.addAttribute("user", userService.editUser(id));
        return "user-form";
    }

    @GetMapping("/userDelete/{id}")
    public String deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
        return "plants";
    }


}
