package pl.szymonstankowski.floraManager.user;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.szymonstankowski.floraManager.plant.Plant;
import pl.szymonstankowski.floraManager.plant.PlantService;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping
public class UserController {

    private final UserService userService;

    private final PlantService plantService;

    public UserController(UserService userService, PlantService plantService) {
        this.userService = userService;

        this.plantService = plantService;
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
    public String userPage(@PathVariable Long id, Model model1, Model model2) {
        List<Plant> plantList = plantService.showPlants();
        User user1 = userService.showUser(id);
        model1.addAttribute("user", user1);
        model2.addAttribute("plant", plantList);
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
