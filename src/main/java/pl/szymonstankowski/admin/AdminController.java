package pl.szymonstankowski.admin;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import pl.szymonstankowski.plant.Plant;
import pl.szymonstankowski.plant.PlantService;
import pl.szymonstankowski.user.UserService;
import pl.szymonstankowski.userPlants.UserPlants;
import pl.szymonstankowski.userPlants.UserPlantsService;

import javax.validation.Valid;
import java.time.LocalDate;

@Controller
@RequestMapping("/admin")
public class AdminController {

    private final PlantService plantService;
    private final UserPlantsService userPlantsService;
    private final UserService userService;

    public AdminController(PlantService plantService, UserPlantsService userPlantsService, UserService userService) {
        this.plantService = plantService;
        this.userPlantsService = userPlantsService;
        this.userService = userService;
    }

    @GetMapping("/console")
    public String managePlants(Model model) {
        model.addAttribute("plants", plantService.getPlants());
        model.addAttribute("users", userService.getUsers());
        return "admin-console";
    }

    @GetMapping("/plant")
    public String newPlantByAdmin(Model model) {
        model.addAttribute("plant", new Plant());
        return "new-admin-plant";
    }

    @PostMapping("/createPlant")
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

    @GetMapping("/markPlant/{id}/{mark}")
    public String markNotActive(@PathVariable Long id, @PathVariable int mark) {
        Plant plant = plantService.findPlantById(id);
        if (mark == 0) {
            plant.setActive(false);
        } else if (mark == 1) {
            plant.setActive(true);
        }
        plantService.savePlant(plant);
        return "redirect:/adminDashboard";
    }

    @GetMapping("/resetClock/{userPlantId}/{plantId}")
    public String podlano(@PathVariable Long userPlantId, @PathVariable Long plantId) {
        UserPlants userPlant = userPlantsService.findPlantById(userPlantId);
        Plant plant = plantService.findPlantById(plantId);

        userPlant.setLocalDate(LocalDate.now());
        userPlant.setNextWateringDate(LocalDate.now().plusDays(plant.getWateringInterval()));

        userPlantsService.savePlant(userPlant);
        return "redirect:/dashboard";
    }

    @DeleteMapping("/deleteUserByAdmin/{id}")
    public String deleteUserByAdmin(@PathVariable Long id) {

        userPlantsService.findAllUserPlantsByUserId(id)
                .forEach(userPlants -> userPlantsService.deleteUserPlant(userPlants.getId()));

        userService.deleteUserById(id);
        return "redirect:/adminDashboard";
    }

}
