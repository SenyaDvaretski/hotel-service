package com.hotelservice.hotelApi.models;


import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.Accessors;
import java.util.UUID;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity(name = "hotels")
@Accessors(chain = true)
@Data
public class Hotel implements Serializable {

    @Id
    private UUID id;

    private String name;
    private String type;
    private String description;
    private String address;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "hotel_id", updatable = false)
    private List<Room> rooms = new ArrayList<>();

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "hotel_id", updatable = false)
    private List<Excursion> excursions = new ArrayList<>();

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "hotel_id",updatable = false)
    private List<AdditionalService> additionalServices = new ArrayList<>();

}
