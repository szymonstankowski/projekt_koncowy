package pl.szymonstankowski.floraManager.userPlant;

import org.springframework.stereotype.Controller;

@Controller
public class UserPlantController {

    private final UserPlantService userPlantService;

    public UserPlantController(UserPlantService userPlantService) {
        this.userPlantService = userPlantService;
    }


}
