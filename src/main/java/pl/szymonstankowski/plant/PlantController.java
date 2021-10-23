package pl.szymonstankowski.plant;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;


import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Controller
//TODO MM: Podobnie jak w adminie. Zacznij tutaj od @RequestMapping("/plants"))
public class PlantController {

    private final PlantService plantService;

    public PlantController(PlantService plantService) {
        this.plantService = plantService;
    }

    @GetMapping("/")
    public String homePage(Model model) {
        List<Plant> plants = plantService.getPlants();
        //TODO MM: no weź... w streamach ładniej
        //       var nonEditablePlants = plantService.getPlants()
        //                .stream()
        //                .filter(plant -> !plant.isEditable())
        //                .filter(Plant::isEditable)
        //                .collect(Collectors.toList());
        List<Plant> nonEditablePlantList = new ArrayList<>();
        for (Plant plant : plants) {
            if (!plant.isEditable() && plant.isActive()) {
                nonEditablePlantList.add(plant);
            }
        }
        model.addAttribute("listOfPlants", nonEditablePlantList);
        return "home-page";
    }

}
