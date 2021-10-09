package pl.szymonstankowski.userPlants;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface UserPlantsRepository extends JpaRepository<UserPlants, Long> {


    @Query("SELECT '*' FROM UserPlants p WHERE userId = ?1")
    public List<UserPlants> findUserPlantsByUserName(String name);


}
