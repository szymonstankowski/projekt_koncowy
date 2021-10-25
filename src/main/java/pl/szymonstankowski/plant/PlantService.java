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

    public void savePlant(Plant plant){

        plantRepository.save(plant);
    }
     public void deletePlant(Plant plant){
        plantRepository.delete(plant);
    }
    public void deletePlantById(Long id){
        plantRepository.deleteById(id);
    }


}
