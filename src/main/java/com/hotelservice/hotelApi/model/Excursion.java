package com.hotelservice.hotelApi.model;

import lombok.Data;
import lombok.experimental.Accessors;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Min;
import java.util.UUID;

@Entity(name="excursions")
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
    private Double price;
    private Boolean enabled;

}
