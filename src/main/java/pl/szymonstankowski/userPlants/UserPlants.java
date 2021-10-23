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

    //TODO MM: Ok jest jakaś data, ale nie wiem data czego. Pola na encjach powinny mieć wymiar biznesowy (techniczne też
    //TODO MM: znajdziesz, ale będzie ich zdecydowanie mniej). Moźnaby doczepić dlaczego używasz LocalDate a nie LocalDateTime ale do szczegół.
    private LocalDate localDate;

    //TODO MM: To jest okropne. Dlaczego in polish? Moźnaby doczepić dlaczego używasz LocalDate a nie LocalDateTime ale do szczegół.
    private LocalDate dataKolejnegoPodlania;

}
