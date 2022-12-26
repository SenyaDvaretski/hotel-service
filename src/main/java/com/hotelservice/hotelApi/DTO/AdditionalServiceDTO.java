package com.hotelservice.hotelApi.DTO;

import jakarta.persistence.Id;
import lombok.Data;

@Data
public class AdditionalServiceDTO {

    private String hotel_id;
    private String name;
    private String type;
    private String description;
    private long price;
    private boolean enabled;
}
