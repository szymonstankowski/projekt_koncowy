package pl.szymonstankowski.user;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import pl.szymonstankowski.plant.PlantService;
import pl.szymonstankowski.userPlants.UserPlants;
import pl.szymonstankowski.userPlants.UserPlantsService;

import java.security.Principal;
import java.util.List;

@Controller
//TODO MM: @RequestMapping("/user")
public class UserController {

    private final UserService userService;
    //TODO MM: Nieużywany
    private final PlantService plantService;
    private final UserPlantsService userPlantsService;

    public UserController(UserService userService, PlantService plantService, UserPlantsService userPlantsService) {
        this.userService = userService;
        this.plantService = plantService;
        this.userPlantsService = userPlantsService;
    }

    //TODO MM: Autentykacje mógłbyś uzyskać za pomocą adnotacji @Secured albo @PreAuthorize ale podanie principala też działą
    //TODO MM: Nazwij metodę zbieżnie do rzeczownika w pathie- dashboard, getDashboard ....
    @GetMapping("/dashboard")
    public String userPage(Model model, Principal principal){
        String name = principal.getName();
        User user = userService.getUserByName(name);
        model.addAttribute("user", user);
        //TODO MM: tutaj już nie musisz walić w repozytorium. Wystarczy że zrobisz user.getUserPlants() to hibernate
        //TODO MM: automatycznie pobierze kolekcje roślinek. Warunek jest taki że na tej metodzie powinieneś postawić @Transactional(readOnly=true)
        List<UserPlants> userPlants = userPlantsService.findAllUserPlantsByUserId(user.getId());
        model.addAttribute("userPlants", userPlants);
        return "user-page";
    }

    @GetMapping("/deleteUser")
    //TODO MM: @DeleteMapping
    public String deleteUser(Principal principal){

        String name = principal.getName();
        User user = userService.getUserByName(name);
        //TODO MM: tutaj można stream, kaskadę itd.
        List<UserPlants> allUserPlantsByUser = userPlantsService.findAllUserPlantsByUserId(user.getId());
        for (UserPlants userPlants : allUserPlantsByUser) {
            userPlantsService.deleteUserPlant(userPlants.getId());
        }
        userService.deleteUserById(user.getId());
        return "redirect:/";
    }

}
