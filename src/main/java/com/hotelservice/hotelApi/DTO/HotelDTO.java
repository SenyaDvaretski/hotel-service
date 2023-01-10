package com.hotelservice.hotelApi.DTO;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class HotelDTO {

    private String name;
    private String type;
    private String description;
    private String address;
    private List<RoomDTO> rooms = new ArrayList<>();
    private List<AdditionalServiceDTO> services = new ArrayList<>();
    private List<ExcursionDTO> excursions = new ArrayList<>();

}
