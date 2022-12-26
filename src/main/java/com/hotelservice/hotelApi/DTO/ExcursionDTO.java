package com.hotelservice.hotelApi.DTO;

import lombok.Data;

@Data
public class ExcursionDTO {

    private String hotel_id;
    private String name;
    private String description;
    private long price;
    private boolean enabled;

}
