package com.hotelservice.hotelApi.service;

import com.hotelservice.hotelApi.DTO.RoomDTO;
import com.hotelservice.hotelApi.constant.CommonExceptionStatus;
import com.hotelservice.hotelApi.exception.CommonException;
import com.hotelservice.hotelApi.mappers.RoomListMapper;
import com.hotelservice.hotelApi.mappers.RoomMapper;
import com.hotelservice.hotelApi.models.Hotel;
import com.hotelservice.hotelApi.models.Room;
import com.hotelservice.hotelApi.repository.HotelRepository;
import com.hotelservice.hotelApi.repository.RoomRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@AllArgsConstructor
public class RoomService {

    public final RoomRepository roomRepository;
    public final HotelRepository hotelRepository;

    private RoomListMapper roomListMapper;
    private RoomMapper roomMapper;

    public RoomDTO addRoom(String hotelName, RoomDTO roomDTO) throws CommonException {
        Optional<Hotel> hotel = hotelRepository.findByName(hotelName);
        Room room = roomMapper.toEntity(roomDTO);
        if(hotel.isPresent()){
            room.setId(UUID.randomUUID());
            room.setHotelId(hotel.get().getId());
            roomRepository.save(room);
            return roomDTO;
        }
        else{
            throw new CommonException(CommonExceptionStatus.HOTEL_NOT_FOUND,
                    "Unable to add room: hotel with this name is not found",
                    HttpStatus.NOT_FOUND);
        }
    }

    public RoomDTO getRoom(String hotelName, int number) throws CommonException {
        Optional<Hotel> hotel = hotelRepository.findByName(hotelName);
        if(hotel.isPresent()){
            Optional<Room> room = roomRepository.
                    findByHotelIdAndNumber(hotel.get().getId(), number);
            if(room.isPresent()){
                return roomMapper.toDTO(room.get());
            }
            throw new CommonException(CommonExceptionStatus.ROOM_NOT_FOUND,
                                        "Unable to get room: room with this number is not found",
                                        HttpStatus.NOT_FOUND);
        }
        throw new CommonException(CommonExceptionStatus.HOTEL_NOT_FOUND,
                                    "Unable to get room: hotel with this name is not found",
                                    HttpStatus.NOT_FOUND);
    }

    public List<RoomDTO> getAllRooms(String hotelName) throws CommonException {
        Optional<Hotel> hotel = hotelRepository.findByName(hotelName);
        if(hotel.isPresent()){
            return roomListMapper.toDTOList(hotel.get().getRooms());
        }
        throw new CommonException(CommonExceptionStatus.HOTEL_NOT_FOUND,
                "Unable to get get all rooms: hotel with this name is not found",
                HttpStatus.NOT_FOUND);
    }

    public RoomDTO updateRoom(String hotelName, RoomDTO roomDTO) throws CommonException {
        Optional<Hotel> hotel = hotelRepository.findByName(hotelName);
        if(hotel.isPresent())
        {
            Optional<Room> room = roomRepository.
                    findByHotelIdAndNumber(hotel.get().getId(), roomDTO.getNumber());
            if(room.isPresent())
            {
                roomMapper.updateRoomFromDTO(roomDTO, room.get());
                return roomDTO;
            }
            else{
                throw new CommonException(CommonExceptionStatus.ROOM_NOT_FOUND,
                        "Unable to update room: room with this number is not found",
                        HttpStatus.NOT_FOUND);
            }
        }
        else{
            throw new CommonException(CommonExceptionStatus.HOTEL_NOT_FOUND,
                    "Unable to update room: hotel with this name is not found",
                    HttpStatus.NOT_FOUND);
        }
    }

    public RoomDTO deleteRoom(String hotelName, int number) throws CommonException {
        Optional<Hotel> opt_hotel = hotelRepository.findByName(hotelName);
        if(opt_hotel.isPresent()){
            Optional<Room> opt_room = roomRepository.
                    findByHotelIdAndNumber(opt_hotel.get().getId(), number);
            if(opt_room.isPresent()){
                roomRepository.delete(opt_room.get());
                return roomMapper.toDTO(opt_room.get());
            }
            throw new CommonException(CommonExceptionStatus.ROOM_NOT_FOUND,
                    "Unable to delete room: room with this number is not found",
                    HttpStatus.NOT_FOUND);
        }
        throw new CommonException(CommonExceptionStatus.HOTEL_NOT_FOUND,
                "Unable to delete room: hotel with this name is not found",
                HttpStatus.NOT_FOUND);
    }

    public List<RoomDTO> getAllAvailableRooms(String hotelName, Boolean isAvailable) throws CommonException {
        Optional<Hotel> hotel = hotelRepository.findByName(hotelName);
        if(hotel.isPresent()){
                return roomListMapper.toDTOList(roomRepository.
                        findByHotelIdAndAvailable(hotel.get().getId(), isAvailable));
        }
        throw new CommonException(CommonExceptionStatus.HOTEL_NOT_FOUND,
                "Unable to delete room: hotel with this name is not found",
                HttpStatus.NOT_FOUND);
    }

    public RoomDTO setRoomAvailable(String hotelName, int roomNumber, Boolean availability) throws CommonException {
        Optional<Hotel> opt_hotel = hotelRepository.findByName(hotelName);
        if (opt_hotel.isPresent()) {
            Optional<Room> opt_room = roomRepository.findByHotelIdAndNumber(opt_hotel.get().getId(), roomNumber);
            if (opt_room.isPresent()) {
                opt_room.get().setAvailable(availability);
                roomRepository.save(opt_room.get());
                return roomMapper.toDTO(opt_room.get());
            }
            throw new CommonException(CommonExceptionStatus.ROOM_NOT_FOUND,
                    "Unable to set room availability: room with this number is not found",
                    HttpStatus.NOT_FOUND);
        }
        throw new CommonException(CommonExceptionStatus.HOTEL_NOT_FOUND,
                "Unable to set room availability: hotel with this name is not found",
                HttpStatus.NOT_FOUND);
    }

}
