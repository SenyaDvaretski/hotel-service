package com.hotelservice.hotelApi.models;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.Accessors;
import java.util.UUID;

@Entity(name="excursions")
@Accessors(chain = true)
@Data

public class Excursion {

    @Id
    private UUID id;

    private UUID hotel_id;
    private String name;
    private String description;
    private double price;
    private boolean enabled;

}
