package pl.szymonstankowski.user;


import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
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
    @Transactional(readOnly=true)
    @GetMapping("/dashboard")
    public String getDashboard(Model model, Principal principal){
        String name = principal.getName();
        User user = userService.getUserByName(name);
        model.addAttribute("user", user);
        model.addAttribute("userPlants", user.getUserPlants());
        return "user-page";
    }

    @DeleteMapping("/deleteUser")
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

}
