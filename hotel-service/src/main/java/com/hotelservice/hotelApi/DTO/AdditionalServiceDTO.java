package com.hotelservice.hotelApi.DTO;

import lombok.Data;

import lombok.experimental.Accessors;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

@Accessors(chain = true)
@Data
public class AdditionalServiceDTO implements BaseDTO{

    @NotBlank
    @Size(max = 40)
    private String name;
    @Size(max = 255)
    private String description;
    @NotNull
    @DecimalMin(value = "0", message = "Price should be positive")
    private Double price;
    private Boolean enabled;
    private List<String> tags;
}
