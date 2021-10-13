package pl.szymonstankowski.plant;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.time.LocalDate;

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


}
