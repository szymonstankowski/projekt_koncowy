package pl.szymonstankowski.floraManager.plant;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/plants")
public class PlantController {

    private PlantService plantService;

    public PlantController(PlantService plantService) {
        this.plantService = plantService;
    }

    @GetMapping
    public String showPlants(Model model){
        List<Plant> plantList = plantService.showPlants();
        model.addAttribute("plant", plantList);
        return "plant-list";
    }


}
