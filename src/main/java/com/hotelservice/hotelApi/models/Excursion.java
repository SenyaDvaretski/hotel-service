package com.hotelservice.hotelApi.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import lombok.*;
import lombok.experimental.Accessors;
import java.util.UUID;

@Entity(name="excursions")
@Accessors(chain = true)
@Data

public class Excursion {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "hotel_id")
    private UUID hotelId;
    private String name;
    private String description;
    @Min(value = 0, message = "Price should be positive")
    private double price;
    private boolean enabled;

}
