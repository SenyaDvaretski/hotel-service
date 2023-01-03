package com.hotelservice.hotelApi.models;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.Accessors;
import java.util.UUID;

@Entity(name="additional_services")
@Accessors(chain = true)
@Data

public class AdditionalService {
    @Id
    private UUID id;
    private String hotel_id;

    private String name;
    private String type;
    private String description;
    private double price;
    private boolean enabled;
}