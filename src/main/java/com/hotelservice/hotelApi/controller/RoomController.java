package com.hotelservice.hotelApi.controller;

import com.hotelservice.hotelApi.DTO.BaseDTO;
import com.hotelservice.hotelApi.DTO.RoomDTO;
import com.hotelservice.hotelApi.exception.CommonException;
import com.hotelservice.hotelApi.mappers.SuccessfulResponseMapper;
import com.hotelservice.hotelApi.repository.HotelRepository;
import com.hotelservice.hotelApi.repository.RoomRepository;
import com.hotelservice.hotelApi.service.RoomService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping(path = "hotels/{hotel_name}/rooms")
@AllArgsConstructor
public class RoomController {
    private final RoomService roomService;

    @GetMapping
    public ResponseEntity<Iterable<RoomDTO>> getAllRooms(@PathVariable("hotel_name") String hotelName) throws CommonException {
        return new ResponseEntity<>(roomService.getAllRooms(hotelName), HttpStatus.OK);
    }


    //check if this works
    @GetMapping("?{available}")
    public ResponseEntity<Iterable<RoomDTO>> getAllAvailableRooms(@PathVariable("hotel_name") String hotelName,
                                                                    @PathVariable("available") Boolean isAvailable) throws CommonException {
        return new ResponseEntity<>(roomService.getAllAvailableRooms(hotelName, isAvailable), HttpStatus.OK);
    }

    @GetMapping("/{room_number}")
    public ResponseEntity<BaseDTO> getRoom(@PathVariable("hotel_name") String hotelName,
                                            @PathVariable("room_number") int roomNumber) throws CommonException {
        return new ResponseEntity<>(roomService.getRoom(hotelName, roomNumber), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<BaseDTO> addRoom(@PathVariable("hotel_name") String hotelName,
                                           @RequestBody RoomDTO roomDTO) throws CommonException {
        return new ResponseEntity<>(roomService.addRoom(hotelName, roomDTO), HttpStatus.OK);
    }

    @DeleteMapping(path = "/{room_number}")
    public ResponseEntity<BaseDTO> deleteRoom(@PathVariable("hotel_name") String hotelName,
                                 @PathVariable("room_number") int roomNumber) throws CommonException {
        return new ResponseEntity<>(roomService.deleteRoom(hotelName, roomNumber), HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<BaseDTO> updateRoom(@PathVariable("hotel_name") String hotelName,
                                 @RequestBody RoomDTO RoomDTO) throws CommonException {
        return new ResponseEntity<>(roomService.updateRoom(hotelName, RoomDTO), HttpStatus.OK);
    }

    @PatchMapping(path = "/{room_number}")
    public ResponseEntity<BaseDTO> setAvailableRoom(@PathVariable("hotel_name") String hotelName,
                                                    @PathVariable("room_number") int roomNumber,
                                                    @RequestBody Boolean availability) throws CommonException {
        return new ResponseEntity<>(roomService.setRoomAvailable(hotelName, roomNumber, availability), HttpStatus.OK);
    }
}
