package com.hotelservice.hotelApi.DTO;

import lombok.Data;

//todo create one id from number and hotel name instead of CONSTRAINT
@Data
public class RoomDTO implements BaseDTO{

    private int number;
    private String type;
    private String description;
    private boolean available;
    private short beds_number;
    private double price;

}
