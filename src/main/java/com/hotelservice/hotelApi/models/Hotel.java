package com.hotelservice.hotelApi.models;


import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity(name="hotels")
@Accessors(chain = true)
@Setter
@Getter
@RequiredArgsConstructor

public class Hotel implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private String id;
}
