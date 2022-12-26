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

public class AdditionalService {
    @Id
    private String name;
}
