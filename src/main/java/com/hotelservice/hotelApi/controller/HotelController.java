package com.hotelservice.hotelApi.controller;

import com.hotelservice.hotelApi.DTO.HotelDTO;
import com.hotelservice.hotelApi.exception.CommonException;
import com.hotelservice.hotelApi.service.HotelService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level= AccessLevel.PRIVATE)
public class HotelController extends BaseController{
    HotelService hotelService;

    @GetMapping
    public ResponseEntity<List<HotelDTO>> getHotels() throws CommonException {
        return hotelService.getAllHotels();
    }

    @GetMapping(path = "/{hotel_name}")
    public ResponseEntity<HotelDTO> getHotel(@PathVariable("hotel_name") String hotelName) throws CommonException {
        return hotelService.getHotel(hotelName);
    }

    @GetMapping(params = "address")
    public ResponseEntity<List<HotelDTO>> deleteHotelByLocation(@RequestParam("address") String location) throws CommonException {
        return hotelService.getAllHotelsByAddress(location);
    }

    @PostMapping
    public ResponseEntity<HotelDTO> addHotel(@RequestBody HotelDTO hotelDTO) throws CommonException {
        return hotelService.addHotel(hotelDTO);
    }

    @DeleteMapping(path = "/{hotel_name}")
    public ResponseEntity<HotelDTO> deleteHotel(@PathVariable("hotel_name") String hotelName) throws CommonException {
        return hotelService.delete(hotelName);
    }

    @PatchMapping
    public ResponseEntity<HotelDTO> updateHotel(@RequestBody HotelDTO hotelDTO) throws CommonException {
            return hotelService.update(hotelDTO);
    }
}