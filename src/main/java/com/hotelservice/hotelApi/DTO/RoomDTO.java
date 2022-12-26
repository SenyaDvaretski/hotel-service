package com.hotelservice.hotelApi.DTO;

import lombok.Data;

@Data
public class RoomDTO {

    private String hotel_id;
    private int number;
    private String type;
    private String description;
    private boolean available;
    private short beds_number;
    private double price;

}
