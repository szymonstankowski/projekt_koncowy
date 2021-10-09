package pl.szymonstankowski.userPlants;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class UserPlantsController {

    @GetMapping("/addNewUserPlant")
    public String addNewUserPlant(Model model){
        model.addAttribute("newPlant", new UserPlants());
        return "newUserPlant-form";
    }
}
