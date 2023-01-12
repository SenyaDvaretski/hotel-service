package com.hotelservice.hotelApi.models;


import lombok.Data;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity(name = "hotels")
@Accessors(chain = true)
@Data
public class Hotel implements Serializable {

    @Id
   // @GeneratedValue(strategy = GenerationType.IDENTITY)
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
