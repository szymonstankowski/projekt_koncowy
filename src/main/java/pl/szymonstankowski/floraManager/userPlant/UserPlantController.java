package pl.szymonstankowski.floraManager.userPlant;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import pl.szymonstankowski.floraManager.plant.Plant;
import pl.szymonstankowski.floraManager.plant.PlantService;

import java.time.LocalDate;
import java.util.List;

@Controller
public class UserPlantController {

    private final UserPlantService userPlantService;
    private final PlantService plantService;
    public UserPlantController(UserPlantService userPlantService, PlantService plantService) {
        this.userPlantService = userPlantService;
        this.plantService = plantService;
    }

    @GetMapping("/addToCollection/{id}")
    public String addToUsersCollection(@PathVariable Long id, Model model){
        UserPlant userPlant = new UserPlant();
        LocalDate date = LocalDate.now();
        Plant plant1 = plantService.findPlant(id);
        userPlant.setName(plant1.getName());
        userPlant.setDescription(plant1.getDescription());
        userPlant.setSunnySpot(plant1.getSunnySpot());
        userPlant.setSoilType(plant1.getSoilType());
        userPlant.setType(plant1.getType());
        userPlant.setVegetationPeriod(plant1.getVegetationPeriod());
        userPlant.setWateringInterval(plant1.getWateringInterval());
        userPlant.setPlantDate(date);
        userPlantService.addUserPlant(userPlant);
        List<UserPlant> userPlantList = userPlantService.showUserPlants();
        model.addAttribute("userPlants", userPlantList);
        return "user-page";
    }

    @GetMapping("/deletePlant/{id}")
    public String deleteUserPlant(@PathVariable Long id, Model model){
        userPlantService.deleteUserPlant(id);
        model.addAttribute("userPlants", userPlantService.showUserPlants());
        return "user-page";
    }



}
