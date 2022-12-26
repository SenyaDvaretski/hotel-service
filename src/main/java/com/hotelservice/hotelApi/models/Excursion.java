package com.hotelservice.hotelApi.models;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;

@Entity(name="excursions")
@Getter
@ToString
@RequiredArgsConstructor

public class Excursion {

    @Id
    private String hotel_id;
}
