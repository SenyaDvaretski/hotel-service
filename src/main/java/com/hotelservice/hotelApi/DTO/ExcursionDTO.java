package com.hotelservice.hotelApi.DTO;

import lombok.Data;

@Data
public class ExcursionDTO implements BaseDTO{

    private String name;
    private String description;
    private long price;
    private boolean enabled;

}
