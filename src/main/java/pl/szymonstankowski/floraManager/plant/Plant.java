package pl.szymonstankowski.floraManager.plant;

import lombok.Data;
import lombok.NoArgsConstructor;
import pl.szymonstankowski.floraManager.user.User;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Entity
@Table (name = "plants")
@Data
@NoArgsConstructor
public class Plant {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @NotBlank
    private String name;
    @NotBlank
    private String type;

    @NotBlank
    @Column(length = 1500)
    private String description;

    @NotBlank
    private Boolean sunnySpot;
    @NotBlank
    private String wateringInterval;
    @NotBlank
    private String vegetationPeriod;
    @NotBlank
    private String soilType;



}
