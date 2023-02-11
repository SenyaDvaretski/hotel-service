package com.hotelservice.hotelApi.mappers;

import com.hotelservice.hotelApi.DTO.HotelDTO;
import com.hotelservice.hotelApi.model.Hotel;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring",
        uses = {
            RoomMapper.class,
            ExcursionMapper.class,
            AdditionalServiceMapper.class
        })

public interface HotelMapper {

    HotelMapper INSTANCE = Mappers.getMapper(HotelMapper.class);
    HotelDTO toDTO(Hotel hotel);

    @Mapping(source = "description", target = "description", defaultValue = "No description")
    Hotel toEntity(HotelDTO hotelDTO);

    void updateHotelFromDto(HotelDTO dto, @MappingTarget Hotel entity);
}
