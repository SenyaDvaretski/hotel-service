package com.hotelservice.hotelApi.models;

import lombok.Data;
import lombok.experimental.Accessors;

import javax.persistence.*;
import javax.validation.constraints.DecimalMin;
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
    @Min(value = 1, message = "mes")
    private Integer number;
    private String type;
    private String description;
    private Boolean available;
    @Column(name = "beds_number")
    @Min(value = 1, message = "Beds number should be positive")
    private Short bedsNumber;
    @DecimalMin(value = "0", message = "Price should be positive")
    private Double price;

}