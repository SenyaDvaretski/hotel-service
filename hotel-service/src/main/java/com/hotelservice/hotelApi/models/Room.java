package com.hotelservice.hotelApi.models;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Accessors(chain = true)
@Getter
@Setter
@RequiredArgsConstructor
public class Room {
    @Id
    private String number;

}