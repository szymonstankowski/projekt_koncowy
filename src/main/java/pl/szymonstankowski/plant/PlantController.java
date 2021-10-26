package pl.szymonstankowski.plant;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/plants")
public class PlantController {

    private final PlantService plantService;

    public PlantController(PlantService plantService) {
        this.plantService = plantService;
    }

    @GetMapping()
    public String homePage(Model model) {

        List<Plant> plantList = plantService.getPlants()
                .stream()
                .filter(plant -> !plant.isEditable())
                .filter(Plant::isActive)
                .collect(Collectors.toList());

        model.addAttribute("listOfPlants", plantList);
        return "home-page";
    }

}
