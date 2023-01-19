package com.hotelservice.hotelApi.models;

import lombok.Data;
import lombok.experimental.Accessors;

import javax.persistence.*;
import javax.validation.constraints.Min;
import java.util.UUID;

@Entity(name="excursion")
@Accessors(chain = true)
@Data

public class Excursion {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Column(name = "hotel_id")
    private UUID hotelId;
    private String name;
    private String description;
    @Min(value = 0, message = "Price should be positive")
    private double price;
    private boolean enabled;

}
