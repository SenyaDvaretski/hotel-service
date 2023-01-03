package com.hotelservice.hotelApi.DTO;

import com.hotelservice.hotelApi.models.AdditionalService;
import com.hotelservice.hotelApi.models.Excursion;
import com.hotelservice.hotelApi.models.Room;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Data
public class HotelDTO {

    private String name;
    private String type;
    private String description;
    private String address;
    private List<Room> rooms = new ArrayList<>();
    private List<AdditionalService> services = new ArrayList<>();
    private List<Excursion> excursions = new ArrayList<>();

}
