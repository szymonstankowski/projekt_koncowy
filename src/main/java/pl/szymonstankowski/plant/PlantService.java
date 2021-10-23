package pl.szymonstankowski.plant;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PlantService {

    private final PlantRepository plantRepository;

    public PlantService(PlantRepository plantRepository) {
        this.plantRepository = plantRepository;
    }

    public List<Plant> getPlants(){
        return plantRepository.findAll();
    }

    public Plant findPlantById(Long id){
        return plantRepository.getById(id);
    }

    //TODO MM: tutaj w metodzie masz jedną linię za dużo
    public void savePlant(Plant plant){

        plantRepository.save(plant);
    }
    public void setPlantToNotActive(Plant plant){
        plantRepository.save(plant);
    }
    //TODO MM: nierówne formatowanie + te 2 metody masz nieużywane
    public void deletePlant(Plant plant){
        plantRepository.delete(plant);
    }
    public void deletePlantById(Long id){
        plantRepository.deleteById(id);
    }


}
