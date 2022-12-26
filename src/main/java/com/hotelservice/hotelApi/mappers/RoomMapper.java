package com.hotelservice.hotelApi.mappers;

import com.hotelservice.hotelApi.DTO.RoomDTO;
import com.hotelservice.hotelApi.models.Room;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface RoomMapper {
    RoomDTO toDTO(Room room);

    Room toEntity(RoomDTO roomDTO);
}
