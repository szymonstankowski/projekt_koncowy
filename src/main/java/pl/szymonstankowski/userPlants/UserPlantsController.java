package pl.szymonstankowski.userPlants;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import pl.szymonstankowski.plant.Plant;
import pl.szymonstankowski.plant.PlantService;

@Controller
public class UserPlantsController {

    private final PlantService plantService;

    public UserPlantsController(PlantService plantService) {
        this.plantService = plantService;
    }

    @GetMapping("/addNewUserPlant")
    public String addNewUserPlant(Model model){
        model.addAttribute("newPlant", new Plant());
        return "newUserPlant-form";
    }

    @GetMapping("/plantList")
    public String addPlantToCollection(Model model){
        model.addAttribute("plants", plantService.getPlants());
        return "plant-list";
    }

}
