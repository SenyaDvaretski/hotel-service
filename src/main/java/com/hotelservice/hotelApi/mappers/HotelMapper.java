package com.hotelservice.hotelApi.mappers;

import com.hotelservice.hotelApi.DTO.HotelDTO;
import com.hotelservice.hotelApi.models.Hotel;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring", uses = {
        RoomListMapper.class,
        ExcursionListMapper.class,
        AdditionalServiceListMapper.class
})

public interface HotelMapper {

    HotelMapper INSTANCE = Mappers.getMapper(HotelMapper.class);
    HotelDTO toDTO(Hotel hotel);

    Hotel toEntity(HotelDTO hotelDTO);

    void updateHotelFromDto(HotelDTO dto, @MappingTarget Hotel entity);
}
