package pl.szymonstankowski.admin;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import pl.szymonstankowski.plant.Plant;
import pl.szymonstankowski.plant.PlantService;
import pl.szymonstankowski.user.UserService;
import pl.szymonstankowski.userPlants.UserPlants;
import pl.szymonstankowski.userPlants.UserPlantsService;

import javax.validation.Valid;
import java.time.LocalDate;
import java.util.List;

@Controller
public class AdminController {

    private final PlantService plantService;
    private final UserPlantsService userPlantsService;
    private final UserService userService;

    public AdminController(PlantService plantService, UserPlantsService userPlantsService, UserService userService) {
        this.plantService = plantService;
        this.userPlantsService = userPlantsService;
        this.userService = userService;
    }

    @GetMapping("/adminDashboard")
    public String deleteAdminPlants(Model model) {
        model.addAttribute("plants", plantService.getPlants());
        model.addAttribute("users", userService.getUsers());
        return "admin-console";
    }

    @GetMapping("/newPlantByAdmin")
    public String newPlantByAdmin(Model model){
        model.addAttribute("plant", new Plant());
        return "new-admin-plant";
    }

    @PostMapping("/newPlantByAdmin")
    public String createNewPlantByAdmin(@Valid Plant plant, BindingResult result) {

        if (result.hasErrors()) {
            return "redirect:/newPlantByAdmin";
        } else {
            plant.setEditable(false);
            plant.setActive(true);
            plantService.savePlant(plant);
        }
        return "redirect:/adminDashboard";
    }


    @GetMapping("/markNotActiveByAdmin/{id}")
    public String markNotActiveByAdmin(@PathVariable Long id) {
        Plant plant = plantService.findPlantById(id);
        plant.setActive(false);
        plantService.setPlantToNotActive(plant);
        return "redirect:/adminDashboard";
    }

    @GetMapping("/markActiveByAdmin/{id}")
    public String markActiveByAdmin(@PathVariable Long id) {
        Plant plant = plantService.findPlantById(id);
        plant.setActive(true);
        plantService.setPlantToNotActive(plant);
        return "redirect:/adminDashboard";
    }

    @GetMapping("/resetPlantClock/{id}")
    public String podlano(@PathVariable Long id){
        UserPlants userPlant = userPlantsService.findPlantById(id);
        userPlant.setLocalDate(LocalDate.now());

        return "redirect:/dashboard";
    }

    @GetMapping("/deleteUserByAdmin/{id}")
    public String deleteUserByAdmin(@PathVariable Long id){

        List<UserPlants> allUserPlantsByUserId = userPlantsService.findAllUserPlantsByUserId(id);
        for (UserPlants userPlants : allUserPlantsByUserId) {
            userPlantsService.deleteUserPlant(userPlants.getId());
        }
        userService.deleteUserById(id);
        return "redirect:/adminDashboard";
    }




}
