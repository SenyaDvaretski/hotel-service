package com.hotelservice.hotelApi.models;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.UUID;

@Entity(name = "rooms")
@Accessors(chain = true)
@Data
public class Room {

    @Id
    private UUID id;

    @Column(name = "hotel_id")
    private UUID hotelId;
    private int number;
    private String type;
    private String description;
    private boolean available;
    private short beds_number;
    private double price;

}