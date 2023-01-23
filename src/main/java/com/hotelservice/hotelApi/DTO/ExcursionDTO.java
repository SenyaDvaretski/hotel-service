package com.hotelservice.hotelApi.DTO;

import lombok.Data;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
public class ExcursionDTO implements BaseDTO{

    @NotBlank
    private String name;
    @Size(max = 255)
    private String description;
    @NotNull
    @DecimalMin(value = "0", message = "Price should be positive")
    private Double price;
    private Boolean enabled;

}
