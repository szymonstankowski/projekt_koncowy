package pl.szymonstankowski.userPlants;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserPlantsService {

    private final UserPlantsRepository userPlantsRepository;

    public UserPlantsService(UserPlantsRepository userPlantsRepository) {
        this.userPlantsRepository = userPlantsRepository;
    }


    public void saveUserPlants(UserPlants userPlants){
        userPlantsRepository.save(userPlants);
    }
    public List<UserPlants> getAll(){
        return userPlantsRepository.findAll();
    }
    public void deleteUserPlant(Long id){
        userPlantsRepository.deleteById(id);
    }
}
