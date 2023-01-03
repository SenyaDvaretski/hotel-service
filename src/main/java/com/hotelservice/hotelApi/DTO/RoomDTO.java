package com.hotelservice.hotelApi.DTO;

import lombok.Data;

import java.util.UUID;

@Data
public class RoomDTO {

    private int number;
    private String type;
    private String description;
    private boolean available;
    private short beds_number;
    private double price;

}
