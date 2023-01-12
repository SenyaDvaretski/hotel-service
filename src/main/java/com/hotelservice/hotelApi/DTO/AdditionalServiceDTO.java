package com.hotelservice.hotelApi.DTO;

import lombok.Data;

@Data
//todo use lombok anntotation
public class AdditionalServiceDTO implements BaseDTO{

    //todo always add swagger description
    private String name;
    private String type;
    private String description;
    private Double price;
    private Boolean enabled;
}
