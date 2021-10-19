package pl.szymonstankowski.userPlants;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserPlantsRepository extends JpaRepository<UserPlants, Long> {

    List<UserPlants> findAllUserPlantsByUserId(Long id);

}
