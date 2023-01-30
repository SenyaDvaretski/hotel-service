package com.hotelservice.hotelApi.models;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity(name="excursions")
@Getter
@ToString
@RequiredArgsConstructor

public class Excursion {

    @Id
    private String hotel_id;
}
