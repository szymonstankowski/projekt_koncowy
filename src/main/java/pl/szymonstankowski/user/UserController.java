package pl.szymonstankowski.user;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import pl.szymonstankowski.plant.Plant;
import pl.szymonstankowski.plant.PlantService;
import pl.szymonstankowski.userPlants.UserPlants;
import pl.szymonstankowski.userPlants.UserPlantsService;

import java.security.Principal;
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
            model.addAttribute("user", user);
            return "user-page";
        }
    }

    @GetMapping("/userPage")
    @ResponseBody
    public String userPage(){
        return "Tutaj powinna byc lista wszystkich roslin usera";
    }

    @GetMapping("/addToCollection/{id}")
    public String addPlantToUserCollection(@PathVariable Long id, Model model){
        Plant plantById = plantService.findPlantById(id);

        UserPlants userPlant = new UserPlants();

        userPlant.setLocalDate(LocalDate.now());

        userPlantsService.saveUserPlants(userPlant);
        List<UserPlants> userPlants = userPlantsService.getAll();


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

    @GetMapping("/deleteUser")
    public String deleteUser(Model model){
        model.addAttribute("deleteUser", new User());
        return "delete-user-form";
    }

    @PostMapping("/deleteUser")
    public String removeUser(User user, BindingResult result){
        if (result.hasErrors()){
            return "delete-user-form";
        }else {
            userService.deleteUser(user.getName());
            return "redirect:/";
        }
    }


}
