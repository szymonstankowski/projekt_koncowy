package pl.szymonstankowski.floraManager.userPlant;

import org.springframework.stereotype.Service;

@Service
public class UserPlantService {

    private final UserPlantRepository userPlantRepository;


    public UserPlantService(UserPlantRepository userPlantRepository) {
        this.userPlantRepository = userPlantRepository;
    }

    public void createUserPlant(UserPlant userPlant){
        userPlantRepository.save(userPlant);
    }
    public void editUserPlant(Long id){
        userPlantRepository.findById(id);
    }
    public void deleteUserPlant(Long id){
        userPlantRepository.deleteById(id);
    }



}
