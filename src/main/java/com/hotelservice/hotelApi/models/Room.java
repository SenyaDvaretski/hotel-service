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
@IdClass(room_id.class)
public class Room {


    @Id
    private String number;

    private String type;

    private String description;

    private boolean available;

    private short beds_number;

    private double price;

    @Id
    private String hotel_id;

}


@EqualsAndHashCode
@NoArgsConstructor
class room_id implements Serializable{
    String hotel_id;
    String number;
}