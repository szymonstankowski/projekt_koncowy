package pl.szymonstankowski.user;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import pl.szymonstankowski.plant.Plant;
import pl.szymonstankowski.plant.PlantService;
import pl.szymonstankowski.userPlants.UserPlants;
import pl.szymonstankowski.userPlants.UserPlantsService;

import java.time.LocalDate;
import java.util.List;

@Controller
public class UserController {

    private final UserService userService;
    private final PlantService plantService;
    private final UserPlantsService userPlantsService;

    public UserController(UserService userService, PlantService plantService, UserPlantsService userPlantsService) {
        this.userService = userService;
        this.plantService = plantService;
        this.userPlantsService = userPlantsService;
    }


    @GetMapping("/")
    public String homePage(Model model){
        model.addAttribute("listOfPlants", plantService.getPlants());
        return "home-page";
    }

    @GetMapping("/addNewUser")
    public String createUser(Model model){
        model.addAttribute("newUser", new User());
        return "user-form";
    }
    @PostMapping("/addNewUser")
    public String addUser(User user, BindingResult result, Model model){
        if (result.hasErrors()){
            return "redirect:/addNewUser";
        }else {
            userService.saveUser(user);
            model.addAttribute("plants", plantService.getPlants());
            return "user-page";
        }
    }

    @GetMapping("/addToCollection/{id}")
    public String addPlantToUserCollection(@PathVariable Long id, Model model){
        Plant plantById = plantService.findPlantById(id);

        UserPlants userPlant = new UserPlants();

        userPlant.setName(plantById.getName());
        userPlant.setDescription(plantById.getDescription());
        userPlant.setLocalDate(LocalDate.now());
        userPlant.setType(plantById.getType());
        userPlant.setSunnySpot(plantById.isSunnySpot());
        userPlant.setVegetationPeriod(plantById.getVegetationPeriod());
        userPlant.setWateringInterval(plantById.getWateringInterval());
        userPlant.setSoilType(plantById.getSoilType());


        userPlantsService.saveUserPlants(userPlant);
        List<UserPlants> userPlants = userPlantsService.getAll();
        //dodac przekazywanie usera

        model.addAttribute("userPlants", userPlants);
        return "user-plants";
    }
    @GetMapping("/deleteUserPlant/{id}")
    public String deleteUserPlant(@PathVariable Long id, Model model){
        userPlantsService.deleteUserPlant(id);
        List<UserPlants> userPlants = userPlantsService.getAll();
        model.addAttribute("userPlants", userPlants);
        return "user-plants";
    }

}
