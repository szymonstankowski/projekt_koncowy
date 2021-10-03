package pl.szymonstankowski.floraManager.plant;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PlantService {

    private final PlantRepository plantRepository;

    public PlantService(PlantRepository plantRepository) {
        this.plantRepository = plantRepository;
    }


    public List<Plant> showPlants() {
        return plantRepository.findAll();
    }

}
