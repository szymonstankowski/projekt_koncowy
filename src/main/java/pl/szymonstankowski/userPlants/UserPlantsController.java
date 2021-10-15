package pl.szymonstankowski.userPlants;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
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

    @GetMapping("/plantList")
    public String choosePlant(Model model) {
        model.addAttribute("plants", plantService.getPlants());
        return "plants";
    }

    @GetMapping("/addPlant/{id}")
    public String addPlant(@PathVariable Long id, Principal principal, Model model) {
        String name = principal.getName();
        User user = userService.getUserByName(name);
        UserPlants userPlants = new UserPlants();
        userPlants.setLocalDate(LocalDate.now());
        userPlants.setUser(user);
        Plant plant = plantService.findPlantById(id);
        userPlants.setPlant(plant);

        Plant plant1 = userPlants.getPlant();

        userPlantsService.savePlant(userPlants);
        model.addAttribute("user", user);
        model.addAttribute("userPlants", userPlantsService.findAllUserPlantsByUserId(user.getId()));
        model.addAttribute("plant", plant1);
        return "user-page";
    }

    @GetMapping("/deleteUserPlant/{id}")
    public String deleteUserPlant(@PathVariable Long id, Model model) {
        userPlantsService.deleteUserPlant(id);
        List<UserPlants> userPlants = userPlantsService.getAll();
        model.addAttribute("userPlants", userPlants);
        return "user-page";
    }

    //nie mozna usunac plantById bo ma on relacje i jego klucz znajduje sie w tablicyy user_plant
    @GetMapping("/deletePlantByAdmin/{id}")
    public String deleteUserPlantByAdmin(@PathVariable Long id) {
        userPlantsService.deleteUserPlantsByPlantId(id);
        plantService.deletePlantById(id);
        return "redirect:/adminDashboard";
    }

    @GetMapping("/addNewPlant")
    public String addNewPlant(Model model) {
        model.addAttribute("plant", new Plant());
        return "new-plant-form";
    }

    @PostMapping("/createNewPlant")
    public String addNewPlant(Plant plant, BindingResult result, Principal principal, Model model) {

        if (result.hasErrors()) {
            return "redirect:/addNewPlant";
        } else {
            User user = userService.getUserByName(principal.getName());
            plantService.savePlant(plant);
            UserPlants userPlants = new UserPlants();
            userPlants.setLocalDate(LocalDate.now());
            userPlants.setUser(user);

            Plant plant1 = plantService.findPlantById(plant.getId());
            userPlants.setPlant(plant1);
            userPlantsService.savePlant(userPlants);

            model.addAttribute("user", user);
            model.addAttribute("userPlants", userPlantsService.findAllUserPlantsByUserId(user.getId()));
        }
        return "user-page";
    }

}
