package pl.szymonstankowski.userPlants;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import pl.szymonstankowski.plant.Plant;
import pl.szymonstankowski.plant.PlantService;
import pl.szymonstankowski.user.User;
import pl.szymonstankowski.user.UserService;

import java.security.Principal;
import java.time.LocalDate;
import java.util.List;

@Controller
public class UserPlantsController {

    private final PlantService plantService;
    private final UserPlantsService userPlantsService;
    private final UserService userService;

    public UserPlantsController(PlantService plantService, UserPlantsService userPlantsService, UserService userService) {
        this.plantService = plantService;
        this.userPlantsService = userPlantsService;
        this.userService = userService;
    }

    @GetMapping("/addNewUserPlant")
    public String addNewUserPlant(Model model){
        model.addAttribute("newPlant", new Plant());
        return "newUserPlant-form";
    }

    @GetMapping("/plantList")
    public String choosePlant(Model model){
        model.addAttribute("plants", plantService.getPlants());
        return "plants";
    }
    @GetMapping("/addPlant/{id}")
    public String addPlant(@PathVariable Long id, Principal principal, Model model){
        String name = principal.getName();
        User user = userService.getUserByName(name);
        UserPlants userPlants = new UserPlants();
        userPlants.setLocalDate(LocalDate.now());
        userPlants.setUser(user);
        Plant plant = plantService.findPlantById(id);
        userPlants.setPlant(plant);
        userPlantsService.savePlant(userPlants);
        model.addAttribute("user", user);
        model.addAttribute("userPlants", userPlantsService.findAllUserPlantsByUser(user.getId()));
        return "user-page";
    }
    @GetMapping("/addToCollection/{id}")
    public String addPlantToUserCollection(@PathVariable Long id, Model model){

        UserPlants userPlant = new UserPlants();
        userPlant.setLocalDate(LocalDate.now());
        userPlantsService.savePlant(userPlant);
        List<UserPlants> userPlants = userPlantsService.getAll();
        model.addAttribute("userPlants", userPlants);
        return "user-plants";
    }

}
