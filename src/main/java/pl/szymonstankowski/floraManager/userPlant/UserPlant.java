package pl.szymonstankowski.floraManager.userPlant;

import lombok.Data;
import lombok.NoArgsConstructor;
import pl.szymonstankowski.floraManager.user.User;


import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.time.LocalDate;

@Entity
@Table(name = "user_plants")
@Data
@NoArgsConstructor
public class UserPlant {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @ManyToOne
    private User user;

    @NotBlank
    private String name;
    @NotBlank
    private String type;

    @NotBlank
    @Column(length = 1500)
    private String description;
    @NotBlank
    private LocalDate plantDate;
    @NotBlank
    private Boolean sunnySpot;
    @NotBlank
    private String wateringInterval;
    @NotBlank
    private String vegetationPeriod;
    @NotBlank
    private String soilType;


}
