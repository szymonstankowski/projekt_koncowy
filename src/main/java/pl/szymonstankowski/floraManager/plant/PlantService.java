package pl.szymonstankowski.floraManager.plant;

import org.springframework.stereotype.Service;

@Service
public class PlantService {

    private final PlantRepository plantRepository;

    public PlantService(PlantRepository plantRepository) {
        this.plantRepository = plantRepository;
    }

    public void addPlant(Plant plant){
        plantRepository.save(plant);
    }
    public Plant getPlant(Long id){
        return plantRepository.findById(id).orElse(null);
    }
}
