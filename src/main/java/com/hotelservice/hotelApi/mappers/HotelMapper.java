package com.hotelservice.hotelApi.mappers;

import com.hotelservice.hotelApi.DTO.HotelDTO;
import com.hotelservice.hotelApi.models.Hotel;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface HotelMapper {
    HotelDTO toDTO(Hotel hotel);

    Hotel toEntity(HotelDTO hotelDTO);
}
