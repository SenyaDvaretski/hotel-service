package com.hotelservice.hotelApi.mappers;

import com.hotelservice.hotelApi.DTO.RoomDTO;
import com.hotelservice.hotelApi.models.Room;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring", uses = RoomMapper.class)
public interface RoomListMapper {
    RoomListMapper INSTANCE = Mappers.getMapper(RoomListMapper.class);
    List<RoomDTO> toDTOList(List<Room> rooms);

    List<Room> toEntityList(List<RoomDTO> roomsDTO);
}
