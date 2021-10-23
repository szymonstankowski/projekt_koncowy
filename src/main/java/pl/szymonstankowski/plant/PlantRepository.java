package pl.szymonstankowski.plant;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PlantRepository extends JpaRepository<Plant, Long> {

    //TODO MM: Tutaj możesz zostawić puste repozytorium. tę metodę będziesz miał bo jest ona w interfejsie JpaRepository,
    // który rozszerzasz
    List<Plant> findAll();

}
