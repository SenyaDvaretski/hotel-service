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

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@AllArgsConstructor
public class RoomService {

    public final RoomRepository roomRepository;
    public final HotelRepository hotelRepository;

    private RoomListMapper roomListMapper;
    private RoomMapper roomMapper;

    public void save(Room fruitEntity){
        roomRepository.save(fruitEntity);
    }

    public List<Room> getAll(){
        return roomRepository.findAll();
    }

    public void saveAll(List<RoomDTO> RoomDtoList) {
        roomRepository.saveAll(roomListMapper.toEntityList(RoomDtoList));
    }

    public void delete(Room room) {
        roomRepository.delete(room);
    }

    public void deleteAll(List<Room> Rooms) {
        roomRepository.deleteAll(Rooms);
    }

    public HttpStatus update(String hotelName, RoomDTO roomDTO)
    {
        Optional<Hotel> opt_hotel = hotelRepository.findByName(hotelName);
        if(opt_hotel.isPresent())
        {
            Optional<Room> opt_room = roomRepository.findByHotelIdAndNumber(opt_hotel.get().getId(),
                    roomDTO.getNumber());
            if(opt_room.isPresent())
            {
                Room roomDb = opt_room.get();
                if(Objects.nonNull(roomDTO.getDescription()) && !"".equals(roomDTO.getDescription()))
                {
                    roomDb.setDescription(roomDTO.getDescription());
                }
                if(Objects.nonNull(roomDTO.getType()) && !"".equals(roomDTO.getType()))
                {
                    roomDb.setType(roomDTO.getType());
                }
                if(Objects.nonNull(roomDTO.getPrice()))
                {
                    roomDb.setPrice(roomDTO.getPrice());
                }
                roomDb.setAvailable(roomDTO.isAvailable());
                roomRepository.save(roomDb);
                return HttpStatus.OK;
            }

        }
        return HttpStatus.NOT_FOUND;
    }

    public List<Room> getAllOccupiedRooms(String hotelName) {
        Optional<Hotel> opt_hotel = hotelRepository.findByName(hotelName);
        if(opt_hotel.isPresent()){
            return roomRepository.findByHotelIdAndFreeTagFalse(opt_hotel.get().getId());
        }
        return new ArrayList<>();
    }

    public List<Room> getAllFreeRooms(String hotelName) {
        Optional<Hotel> opt_hotel = hotelRepository.findByName(hotelName);
        if(opt_hotel.isPresent()){
            return roomRepository.findByHotelIdAndFreeTagTrue(opt_hotel.get().getId());
        }

        return new ArrayList<>();
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

    public HttpStatus setRoomPrice(String hotelName,int roomNumber, double price){
        Optional<Hotel> opt_hotel = hotelRepository.findByName(hotelName);
        if(opt_hotel.isPresent()){
            Optional<Room> opt_room = roomRepository.findByHotelIdAndNumber(opt_hotel.get().getId(),roomNumber);
            if(opt_room.isPresent()){
                roomRepository.save(opt_room.get().setPrice(price));
                return HttpStatus.OK;
            }

        }
        return HttpStatus.NOT_FOUND;
    }
}
