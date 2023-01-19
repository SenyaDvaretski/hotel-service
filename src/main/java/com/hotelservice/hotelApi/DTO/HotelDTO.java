package com.hotelservice.hotelApi.DTO;

import lombok.Data;

@Data
public class HotelDTO implements BaseDTO{

    private String name;
    private String type;
    private String description;
    private String address;
//    private List<RoomDTO> rooms = new ArrayList<>();
//    private List<AdditionalServiceDTO> services = new ArrayList<>();
//    private List<ExcursionDTO> additionalServices = new ArrayList<>();

}
