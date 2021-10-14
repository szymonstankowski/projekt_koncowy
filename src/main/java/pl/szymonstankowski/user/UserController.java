package pl.szymonstankowski.user;


import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import pl.szymonstankowski.plant.Plant;
import pl.szymonstankowski.plant.PlantService;
import pl.szymonstankowski.userPlants.UserPlants;
import pl.szymonstankowski.userPlants.UserPlantsService;

import javax.validation.Valid;
import java.security.Principal;
import java.time.LocalDate;
import java.util.List;

@Controller
public class UserController {

    private final UserService userService;
    private final PlantService plantService;
    private final UserPlantsService userPlantsService;


    public UserController(UserService userService, PlantService plantService, UserPlantsService userPlantsService) {
        this.userService = userService;
        this.plantService = plantService;
        this.userPlantsService = userPlantsService;
    }



    @GetMapping("/addNewUser")
    public String createUser(Model model){
        model.addAttribute("user", new User());
        return "user-form";
    }
    @PostMapping("/addNewUser")
    public String addUser(User user, BindingResult result, Model model){
        if (result.hasErrors()){
            return "user-form";
        }else {
            userService.saveUser(user);
            model.addAttribute("user", user);
            return "redirect:/login";
        }
    }

    @GetMapping("/dashboard")
    public String userPage(Model model, Principal principal){
        String name = principal.getName();
        User user = userService.getUserByName(name);
        model.addAttribute("user", user);
        List<UserPlants> userPlants = userPlantsService.findAllUserPlantsByUser(user.getId());
        model.addAttribute("userPlants", userPlants);
        return "user-page";
    }

    @GetMapping("/deleteUser")
    public String deleteUser(Principal principal){

        String name = principal.getName();
        User user = userService.getUserByName(name);
        userPlantsService.findAllUserPlantsByUser(user.getId());
        userPlantsService.deleteAllUserPlantsByUserId(user.getId());
        userService.deleteUserById(user.getId());
        return "redirect:/";
    }

}
