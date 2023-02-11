package com.hotelservice.hotelApi.DTO;

import com.hotelservice.hotelApi.constant.RoomType;
import lombok.Data;
import lombok.experimental.Accessors;

import javax.persistence.Column;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Set;

@Accessors(chain = true)
@Data
public class RoomDTO implements BaseDTO{
    @NotNull
    @Min(value = 1)
    @Column(unique = true)
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
    @NotNull
    private Set<String> tags;

}
