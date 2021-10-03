package pl.szymonstankowski.floraManager.userPlant;

import org.springframework.stereotype.Service;
import pl.szymonstankowski.floraManager.plant.Plant;

import java.util.List;

@Service
public class UserPlantService {

    private final UserPlantRepository userPlantRepository;


    public UserPlantService(UserPlantRepository userPlantRepository) {
        this.userPlantRepository = userPlantRepository;
    }

    public void addUserPlant(UserPlant userPlant){
        userPlantRepository.save(userPlant);
    }

    public List<UserPlant> showUserPlants(){
        return userPlantRepository.findAll();
    }

    public void editUserPlant(Long id){
        userPlantRepository.findById(id);
    }
    public void deleteUserPlant(Long id){
        userPlantRepository.deleteById(id);
    }



}
