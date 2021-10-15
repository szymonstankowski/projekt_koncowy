package pl.szymonstankowski.plant;

import lombok.Data;
import pl.szymonstankowski.userPlants.UserPlants;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.List;

@Entity
@Data
@Table(name = "plants")
public class Plant {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Size(max = 1500)
    private String description;
    private String name;
    private String soilType;
    private String type;
    private String vegetationPeriod;
    private String wateringInterval;
    private String sunnySpot;
    private boolean editable;





}
