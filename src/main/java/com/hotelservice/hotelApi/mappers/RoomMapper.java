package com.hotelservice.hotelApi.mappers;

import com.hotelservice.hotelApi.DTO.HotelDTO;
import com.hotelservice.hotelApi.DTO.RoomDTO;
import com.hotelservice.hotelApi.models.Hotel;
import com.hotelservice.hotelApi.models.Room;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface RoomMapper {
    RoomDTO toDTO(Room room);
    Room toEntity(RoomDTO roomDTO);
    void updateRoomFromDto(RoomDTO dto, @MappingTarget Room entity);
}
