package pl.szymonstankowski.userPlants;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import pl.szymonstankowski.user.User;

import java.util.List;

public interface UserPlantsRepository extends JpaRepository<UserPlants, Long> {

    public List<UserPlants> findAllUserPlantsByUserId(Long id);
    
}
