package pl.szymonstankowski.user;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import pl.szymonstankowski.plant.Plant;
import pl.szymonstankowski.plant.PlantService;
import pl.szymonstankowski.userPlants.UserPlants;
import pl.szymonstankowski.userPlants.UserPlantsService;

import java.security.Principal;
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
        List<UserPlants> userPlants = userPlantsService.findAllUserPlantsByUserId(user.getId());
        model.addAttribute("userPlants", userPlants);
        return "user-page";
    }

    @GetMapping("/deleteUser")
    public String deleteUser(Principal principal){

        String name = principal.getName();
        User user = userService.getUserByName(name);
        List<UserPlants> allUserPlantsByUser = userPlantsService.findAllUserPlantsByUserId(user.getId());
        for (UserPlants userPlants : allUserPlantsByUser) {
            userPlantsService.deleteUserPlant(userPlants.getId());
        }
        userService.deleteUserById(user.getId());
        return "redirect:/";
    }
    @GetMapping("/deleteUserByAdmin")
    public String deleteUserByAdmin(Principal principal){
        String name = principal.getName();
        User user = userService.getUserByName(name);
        List<UserPlants> allUserPlantsByUser = userPlantsService.findAllUserPlantsByUserId(user.getId());
        for (UserPlants userPlants : allUserPlantsByUser) {
            userPlantsService.deleteUserPlant(userPlants.getId());
        }
        userService.deleteUserById(user.getId());
        return "redirect:/adminDashboard";
    }

    @GetMapping("/adminDashboard")
    public String deleteAdminPlants(Model model) {
        model.addAttribute("plants", plantService.getPlants());
        model.addAttribute("users", userService.getUsers());
        return "admin-console";
    }

//    @PostMapping("/adminDashboard")
//    public String deletePlant(@PathVariable Long id) {
//        Plant plantById = plantService.findPlantById(id);
//        plantService.deletePlant(plantById);
//        return "redirect:/deleteAdminPlants";
//    }


}
