package com.hotelservice.hotelApi.models;

import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity(name="additional_service")
@Accessors(chain = true)
@Setter
@ToString
@RequiredArgsConstructor

public class AdditionalService {
    @Id
    private String name;
}
