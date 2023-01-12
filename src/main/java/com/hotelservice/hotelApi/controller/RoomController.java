package com.hotelservice.hotelApi.controller;

import com.hotelservice.hotelApi.DTO.RoomDTO;
import com.hotelservice.hotelApi.models.Hotel;
import com.hotelservice.hotelApi.models.Room;
import com.hotelservice.hotelApi.repository.HotelRepository;
import com.hotelservice.hotelApi.repository.RoomRepository;
import com.hotelservice.hotelApi.service.RoomService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.webjars.NotFoundException;

import java.util.Optional;


@RestController
@RequestMapping(path = "hotels/{hotel_name}/rooms")
public class RoomController {

    private final RoomRepository roomRepository;
    private final RoomService roomService;
    private final HotelRepository hotelRepository;

    RoomController(RoomRepository roomRepository, RoomService roomService,
                   HotelRepository hotelRepository)
    {
        this.roomRepository = roomRepository;
        this.roomService = roomService;
        this.hotelRepository = hotelRepository;
    }

//    @GetMapping
//    public Iterable<Room> getAllRooms(){
//        return roomRepository.findAll();
//    }

    @GetMapping
    public Room getRoom(@PathVariable("hotel_name") String hotelName,
                        @RequestBody int roomNumber)
    {
        Optional<Hotel> opt_hotel = hotelRepository.findByName(hotelName);
        if(opt_hotel.isPresent()) {
            Optional<Room> opt_Room = roomRepository.
                                        findByHotelIdAndNumber(opt_hotel.get().getId(), roomNumber);
            if (opt_Room.isPresent()) {
                return opt_Room.get();
            }
        }
        throw new NotFoundException("No required rooms");
    }

    @PostMapping
    public HttpStatus addRoom(@PathVariable("hotel_name") String hotelName,
                              @RequestBody RoomDTO RoomDTO)
    {
        try
        {
            roomService.add(hotelName, RoomDTO);
            return HttpStatus.OK;
        }
        catch (Exception e)
        {
            e.printStackTrace();
            return HttpStatus.BAD_REQUEST;
        }
    }

    @DeleteMapping(path = "/{room_number}")
    public HttpStatus deleteRoom(@PathVariable("hotel_name") String hotelName,
                                 @PathVariable("room_number") int roomNumber)
    {
        Optional<Hotel> opt_Hotel = hotelRepository.findByName(hotelName);
        if(opt_Hotel.isPresent()) {
            Optional<Room> opt_Room = roomRepository.
                                findByHotelIdAndNumber(opt_Hotel.get().getId(), roomNumber);
            if (opt_Room.isPresent()) {
                roomService.delete(hotelName, roomNumber);
                return HttpStatus.OK;
            }
        }
        return HttpStatus.NOT_FOUND;

    }

    @PutMapping
    public HttpStatus updateRoom(@PathVariable("hotel_name") String hotelName,
                                 @RequestBody RoomDTO RoomDTO){
        try{
            return roomService.update(hotelName, RoomDTO);
        }catch (Exception e){
            return HttpStatus.CONFLICT;
        }

    }
}
