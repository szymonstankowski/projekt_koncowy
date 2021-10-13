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
    @ManyToOne
    Plant plant;

    private LocalDate localDate;

}
