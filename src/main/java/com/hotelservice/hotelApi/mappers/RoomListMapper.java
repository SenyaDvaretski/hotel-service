package com.hotelservice.hotelApi.mappers;

import com.hotelservice.hotelApi.DTO.RoomDTO;
import com.hotelservice.hotelApi.models.Room;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring", uses = RoomMapper.class)
public interface RoomListMapper {
    List<RoomDTO> toDTOList(List<Room> rooms);

    List<Room> toEntityList(List<RoomDTO> roomsDTO);
}
