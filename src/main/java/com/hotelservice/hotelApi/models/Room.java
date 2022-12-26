package com.hotelservice.hotelApi.models;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.Accessors;

import java.io.Serializable;

@Entity
@Accessors(chain = true)
@Getter
@Setter
@RequiredArgsConstructor
public class Room {
    @Id
    private String number;

}