package com.hotelservice.hotelApi.services;

import com.hotelservice.hotelApi.DTO.RoomDTO;
import com.hotelservice.hotelApi.mappers.RoomListMapper;
import com.hotelservice.hotelApi.mappers.RoomMapper;
import com.hotelservice.hotelApi.models.Hotel;
import com.hotelservice.hotelApi.models.Room;
import com.hotelservice.hotelApi.repositories.HotelRepository;
import com.hotelservice.hotelApi.repositories.RoomRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


/*
* TODO:
*   add exceptions:
*           getAllRooms
*           deleteRoom
*           getRoom
*/

@Service
@AllArgsConstructor
public class RoomService {

    public final RoomRepository roomRepository;
    public final HotelRepository hotelRepository;

    private RoomListMapper roomListMapper;
    private RoomMapper roomMapper;

    public HttpStatus addRoom(String hotelName, RoomDTO roomDTO){
        Optional<Hotel> hotel = hotelRepository.findByName(hotelName);
        Room room = roomMapper.toEntity(roomDTO);
        if(hotel.isPresent()){
            room.setHotelId(hotel.get().getId());
            roomRepository.save(room);
            return HttpStatus.CREATED;
        }else return HttpStatus.NOT_FOUND;
    }

    // create custom exception with http status
    public RoomDTO getRoom(String hotelName, int number) throws Exception {
        Optional<Hotel> hotel = hotelRepository.findByName(hotelName);
        if(hotel.isPresent()){
            Optional<Room> room = roomRepository.
                    findByHotelIdAndNumber(hotel.get().getId(), number);
            if(room.isPresent()){
                return roomMapper.toDTO(room.get());
            }
            throw new Exception();
        }
        throw new Exception();
    }

    public HttpStatus update(String hotelName, RoomDTO roomDTO)
    {
        Optional<Hotel> hotel = hotelRepository.findByName(hotelName);
        if(hotel.isPresent())
        {
            Optional<Room> room = roomRepository.
                    findByHotelIdAndNumber(hotel.get().getId(), roomDTO.getNumber());
            if(room.isPresent())
            {
                roomMapper.updateRoomFromDto(roomDTO, room.get());
                return HttpStatus.OK;
            }

        }
        return HttpStatus.NOT_FOUND;
    }

    // probably throw exception with http status instead of just http status
    public HttpStatus deleteRoom(String hotelName, int number){
        Optional<Hotel> hotel = hotelRepository.findByName(hotelName);
        if(hotel.isPresent()){
            Optional<Room> room = roomRepository.
                    findByHotelIdAndNumber(hotel.get().getId(), number);
            if(room.isPresent()){
                roomRepository.delete(room.get());
                return HttpStatus.OK;
            }
            return HttpStatus.NOT_FOUND;
        }
        return HttpStatus.NOT_FOUND;
    }

    // create custom exception with http status
    public List<RoomDTO> getAllRooms(String hotelName) throws Exception {
        Optional<Hotel> hotel = hotelRepository.findByName(hotelName);
        if(hotel.isPresent()){
            return roomListMapper.toDTOList(hotel.get().getRooms());
        }
        throw new Exception("not found");
    }

    public List<RoomDTO> getAllAvailableRooms(String hotelName) throws Exception {
        Optional<Hotel> hotel = hotelRepository.findByName(hotelName);
        if(hotel.isPresent()){
            return roomListMapper.toDTOList(roomRepository.
                            findByHotelIdAndAvailableTrue(hotel.get().getId()));
        }
        throw new Exception("not found");
    }

    public List<RoomDTO> getAllOccupiedRooms(String hotelName) throws Exception {
        Optional<Hotel> hotel = hotelRepository.findByName(hotelName);
        if(hotel.isPresent()){
            return roomListMapper.toDTOList(roomRepository.
                    findByHotelIdAndAvailableFalse(hotel.get().getId()));
        }
        throw new Exception("not found");
    }

    public HttpStatus setRoomAvailable(String hotelName, int roomNumber, boolean freeTag) {
        Optional<Hotel> opt_hotel = hotelRepository.findByName(hotelName);
        if(opt_hotel.isPresent()){
            Optional<Room> opt_room = roomRepository.findByHotelIdAndNumber(opt_hotel.get().getId(),roomNumber);
            if(opt_room.isPresent()){
                roomRepository.save(opt_room.get().setAvailable(freeTag));
                return HttpStatus.OK;
            }
        }
        return HttpStatus.NOT_FOUND;
    }

}
