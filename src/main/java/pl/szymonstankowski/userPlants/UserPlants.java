package pl.szymonstankowski.userPlants;

import lombok.Data;
import pl.szymonstankowski.plant.Plant;
import pl.szymonstankowski.user.User;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.util.List;

@Entity
@Data
@Table(name = "user_plants")
public class UserPlants{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    User user;

    @Size(max = 1500)
    private String description;
    private String name;
    private String soilType;
    private String type;
    private String vegetationPeriod;
    private String wateringInterval;
    private boolean sunnySpot;
    private LocalDate localDate;
}
