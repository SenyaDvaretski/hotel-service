package com.hotelservice.hotelApi.models;

import lombok.Data;
import lombok.experimental.Accessors;

import javax.persistence.*;
import javax.validation.constraints.Min;
import java.util.UUID;

@Entity(name = "rooms")
@Accessors(chain = true)
@Data
public class Room {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Column(name = "hotel_id")
    private UUID hotelId;
    private int number;
    private String type;
    private String description;
    private boolean available;
    @Min(value = 0, message = "Beds number should be positive")
    private short beds_number;
    @Min(value = 0, message = "Price should be positive")
    private double price;

}