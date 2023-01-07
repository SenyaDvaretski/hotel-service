package com.hotelservice.hotelApi.mappers;

import com.hotelservice.hotelApi.DTO.RoomDTO;
import com.hotelservice.hotelApi.models.Room;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface RoomMapper {
    RoomMapper INSTANCE = Mappers.getMapper(RoomMapper.class);
    RoomDTO toDTO(Room room);
    Room toEntity(RoomDTO roomDTO);
    void updateRoomFromDTO(RoomDTO dto, @MappingTarget Room entity);
}
