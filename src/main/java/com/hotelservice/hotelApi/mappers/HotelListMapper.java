package com.hotelservice.hotelApi.mappers;

import com.hotelservice.hotelApi.DTO.HotelDTO;
import com.hotelservice.hotelApi.models.Hotel;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring", uses = {HotelMapper.class,
                                            RoomMapper.class,
                                            ExcursionMapper.class,
                                            AdditionalServiceMapper.class})

public interface HotelListMapper {
    HotelListMapper INSTANCE = Mappers.getMapper(HotelListMapper.class);
    List<HotelDTO> toDTOList(List<Hotel> hotels);

    List<Hotel> toEntityList(List<HotelDTO> hotelsDTO);
}
