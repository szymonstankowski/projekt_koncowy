package pl.szymonstankowski.plant;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

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

        var nonEditablePlants = plantService.getPlants()
                .stream()
                .filter(plant -> !plant.isEditable())
                .filter(Plant::isEditable)
                .collect(Collectors.toList());

        model.addAttribute("listOfPlants", nonEditablePlants);
        return "home-page";
    }

}
