package com.hotelservice.hotelApi.models;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.Accessors;

import java.io.Serializable;

@Entity(name="additional_service")
@Accessors(chain = true)
@Setter
@ToString
@RequiredArgsConstructor
@IdClass(id.class)

public class AdditionalService {
    @Id
    private String name;
    private String type;
    private String description;
    private long price;
    private boolean enabled;
    @Id
    private String hotel_id;
}

@EqualsAndHashCode
@NoArgsConstructor
class id implements Serializable {
    String hotel_id;
    String name;
}