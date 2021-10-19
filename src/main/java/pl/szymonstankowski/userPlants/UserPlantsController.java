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

    @GetMapping("/userPlantList")
    public String plantList(Model model) {
        List<Plant> plants = plantService.getPlants();
        if (plants.isEmpty()){
            return "redirect:/dashboard";
        }
        for (Plant plant : plants) {
            if (!plant.isActive()){
                plants.add(plant);
            }
            model.addAttribute("plants", plants);
        }
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


    @GetMapping("/addNewPlant")
    public String addNewPlant(Model model) {
        model.addAttribute("plant", new Plant());
        return "new-plant-form";
    }

    @PostMapping("/createNewPlant")
    public String createNewPlant(Plant plant, BindingResult result, Principal principal, Model model) {

        if (result.hasErrors()) {
            return "redirect:/addNewPlant";
        } else {
            User user = userService.getUserByName(principal.getName());
            plant.setEditable(true);
            plant.setActive(true);
            plantService.savePlant(plant);
            UserPlants userPlants = new UserPlants();

            LocalDate date = LocalDate.now();
            userPlants.setLocalDate(date);

            userPlants.setUser(user);

            Plant plant1 = plantService.findPlantById(plant.getId());
            userPlants.setPlant(plant1);

            int interval = Integer.parseInt(plant1.getWateringInterval());

            LocalDate dateAndInterval = date.plusDays(interval);
            userPlants.setDataKolejnegoPodlania(dateAndInterval);

            userPlantsService.savePlant(userPlants);


            model.addAttribute("user", user);
            model.addAttribute("userPlants", userPlantsService.findAllUserPlantsByUserId(user.getId()));
        }
        return "user-page";
    }

}
