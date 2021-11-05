package pl.szymonstankowski.userPlants;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserPlantsService {

    private final UserPlantsRepository userPlantsRepository;

    public UserPlantsService(UserPlantsRepository userPlantsRepository) {
        this.userPlantsRepository = userPlantsRepository;
    }

    public List<UserPlants> findAllUserPlantsByUserId(Long id) {

        return userPlantsRepository.findAllUserPlantsByUserId(id);
    }



    public void deleteUserPlantsByPlantId(Long id) {
        deleteUserPlantsByPlantId(id);
    }

    public UserPlants findPlantById(Long id) {
        return userPlantsRepository.findById(id).orElse(null);
    }

    public void savePlant(UserPlants userPlants) {
        userPlantsRepository.save(userPlants);
    }

    public List<UserPlants> getAll() {
        return userPlantsRepository.findAll();
    }

    public void deleteUserPlant(Long id) {
        userPlantsRepository.deleteById(id);
    }


}
