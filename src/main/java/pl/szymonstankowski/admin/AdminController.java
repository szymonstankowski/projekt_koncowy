package pl.szymonstankowski.admin;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import pl.szymonstankowski.plant.Plant;
import pl.szymonstankowski.plant.PlantService;
import pl.szymonstankowski.user.UserService;
import pl.szymonstankowski.userPlants.UserPlants;
import pl.szymonstankowski.userPlants.UserPlantsService;

import javax.validation.Valid;
import java.time.LocalDate;
import java.util.List;
import java.util.concurrent.atomic.AtomicMarkableReference;

//TODO MM: Fajnie gdyby te kontrolki domenowe rozdzielić (aktualnie wszystkie masz na głównej ścieżce servletu)
//TODO MM: Służy do tego @RequestMapping("/admin")
@Controller
public class AdminController {

    private final PlantService plantService;
    private final UserPlantsService userPlantsService;
    private final UserService userService;

    public AdminController(PlantService plantService, UserPlantsService userPlantsService, UserService userService) {
        this.plantService = plantService;
        this.userPlantsService = userPlantsService;
        this.userService = userService;
    }

    //TODO MM: Nie wolałbyś użyć metody http DELETE? Np. @DeleteMapping? I nazwa endpointu jest nieadekwatna do nazwy metody
    //TODO MM: Ale ostatecznie ta metoda chyba jednak wyciąga dashboard, nic nie kasuje co nie?
    @GetMapping("/adminDashboard")
    public String deleteAdminPlants(Model model) {
        model.addAttribute("plants", plantService.getPlants());
        model.addAttribute("users", userService.getUsers());
        return "admin-console";
    }

    //TODO MM: Lepiej byłoby tutaj użyć metody POST. I generalnie w nazwach ścieżek nie używamy czasowników ;) Ale pewnie o RESTfulu nie mieliście
    //TODO MM: ten endpoint nazwałbym POST /admin/plant (/admin miałbym z @RequestMapping nad klasą, więc w tym miejscu
    //TODO MM: tylko /plant z @PostMapping). Ta uwaga może mieć zastosowanie we wszystkich endpointach (prefix /admin i brak czasowników)
    @GetMapping("/newPlantByAdmin")
    public String newPlantByAdmin(Model model){
        model.addAttribute("plant", new Plant());
        return "new-admin-plant";
    }

    //TODO MM:I tu jest poprawnie POST, ale końcówka powinna się nazywać jw. /plant. I chyba to jest poprawny endpoint do zapisu.
    //TODO MM: W takim razie nie wiem do czego jest ten powyżej (jest do errorów?) :)
    @PostMapping("/newPlantByAdmin")
    public String createNewPlantByAdmin(@Valid Plant plant, BindingResult result) {

        if (result.hasErrors()) {
            return "redirect:/newPlantByAdmin";
        } else {
            plant.setEditable(false);
            plant.setActive(true);
            plantService.savePlant(plant);
        }
        return "redirect:/adminDashboard";
    }

    //TODO MM: takie zmiany to putem możesz robić
    @GetMapping("/markNotActiveByAdmin/{id}")
    public String markNotActiveByAdmin(@PathVariable Long id) {
        Plant plant = plantService.findPlantById(id);
        plant.setActive(false);
        plantService.setPlantToNotActive(plant);
        return "redirect:/adminDashboard";
    }

    //TODO MM: metody markNotActiveByAdmin i markActiveByAdmin -> możesz wydzielić część wspólną w metodzie
    @GetMapping("/markActiveByAdmin/{id}")
    public String markActiveByAdmin(@PathVariable Long id) {
        Plant plant = plantService.findPlantById(id);
        plant.setActive(true);
        plantService.setPlantToNotActive(plant);
        return "redirect:/adminDashboard";
    }

    @GetMapping("/resetPlantClock/{userPlantId}/{plantId}")
    public String podlano(@PathVariable Long userPlantId, @PathVariable Long plantId){
        UserPlants userPlant = userPlantsService.findPlantById(userPlantId);
        Plant plant = plantService.findPlantById(plantId);

        //TODO MM: Dlaczego w bazie nie ustawiełeś longa dla watering level i parsujesz go samodzielnie?
        //TODO MM: I używanie skrótowych nazw to nie jest dobry pomysł (l)
        String wateringInterval = plant.getWateringInterval();
        Long l = Long.parseLong(wateringInterval);

        userPlant.setLocalDate(LocalDate.now());
        userPlant.setDataKolejnegoPodlania(LocalDate.now().plusDays(l));

        userPlantsService.savePlant(userPlant);
        return "redirect:/dashboard";
    }

    @GetMapping("/deleteUserByAdmin/{id}")
    public String deleteUserByAdmin(@PathVariable Long id){

        //TODO MM: Dlaczego nie forEach w lambdzie?
        //        userPlantsService.findAllUserPlantsByUserId(id)
        //                .forEach(userPlants -> userPlantsService.deleteUserPlant(userPlants.getId()));
        List<UserPlants> allUserPlantsByUserId = userPlantsService.findAllUserPlantsByUserId(id);
        for (UserPlants userPlants : allUserPlantsByUserId) {
            userPlantsService.deleteUserPlant(userPlants.getId());
        }
        //TODO MM: Generalnie coś takiego uzyskałbyś za pomocą kaskad na relacjach np. ManyToOne(casacde...) (nie musiałbyś ręcznie tego robić,
        //TODO MM: ale pewnie za daleko wybiega)
        userService.deleteUserById(id);
        return "redirect:/adminDashboard";
    }
}
