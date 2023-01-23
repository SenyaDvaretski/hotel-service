package com.hotelservice.hotelApi.DTO;

import com.hotelservice.hotelApi.constant.RoomType;
import lombok.Data;

import javax.persistence.Column;
import javax.validation.constraints.*;

//todo create one id from number and hotel name instead of CONSTRAINT
@Data
public class RoomDTO implements BaseDTO{
    @NotNull
    @Min(value = 1)
    private int number;
    @NotNull
    private RoomType type;
    @NotBlank
    @Size(max = 255)
    private String description;
    private Boolean available;
    @Column(name = "beds_number")
    @Min(value = 1, message = "Beds number should be more tan zero")
    private Short bedsNumber;
    @NotNull
    @DecimalMin(value = "0", message = "Price should be positive")
    private Double price;

}
