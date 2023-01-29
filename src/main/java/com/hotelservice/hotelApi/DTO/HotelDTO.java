package com.hotelservice.hotelApi.DTO;

import com.hotelservice.hotelApi.constant.HotelType;
import lombok.Data;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Set;

@Accessors(chain = true)
@Data
public class HotelDTO implements BaseDTO{

    @NotBlank
    @Size(min = 1, max = 40)
    private String name;
    @NotNull
    private HotelType type;
    @Size(max = 255)
    private String description;
    @NotBlank
    @Size(max = 255)
    private String address;
    @NotNull
    private Set<String> tags;

    //should i add this lists to dto?

//    private List<RoomDTO> rooms = new ArrayList<>();
//    private List<AdditionalServiceDTO> services = new ArrayList<>();
//    private List<ExcursionDTO> additionalServices = new ArrayList<>();

}
