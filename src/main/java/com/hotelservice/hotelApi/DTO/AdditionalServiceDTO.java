package com.hotelservice.hotelApi.DTO;

import jakarta.persistence.Id;
import lombok.Data;

@Data
public class AdditionalServiceDTO {

    private String name;
    private String type;
    private String description;
    private double price;
    private boolean enabled;
}
