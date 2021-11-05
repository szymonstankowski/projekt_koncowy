package pl.szymonstankowski.userPlants;

import lombok.Data;
import pl.szymonstankowski.plant.Plant;
import pl.szymonstankowski.user.User;

import javax.persistence.*;
import java.time.LocalDate;


@Entity
@Data
@Table(name = "user_plants")
public class UserPlants{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    User user;

    @ManyToOne
    Plant plant;

    private LocalDate localDate;
    private LocalDate nextWateringDate;

}
