package com.hotelservice.hotelApi.controller;

import com.hotelservice.hotelApi.DTO.HotelDTO;
import com.hotelservice.hotelApi.exception.CommonException;
import com.hotelservice.hotelApi.repository.HotelRepository;
import com.hotelservice.hotelApi.response.Response;
import com.hotelservice.hotelApi.service.HotelService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/hotels")
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level= AccessLevel.PRIVATE)
public class HotelController {
    HotelService hotelService;

//    @GetMapping
//    public ResponseEntity<Iterable<Hotel>> getHotels(){
//        return //todo response hotelRepository.findAll();
//    }

    @GetMapping(path = "/{hotel_name}")
    public ResponseEntity<Response> getHotel(@PathVariable("hotel_name") String hotelName) throws CommonException {
        return hotelService.getHotel(hotelName);
    }


   /* @PostMapping
    public HttpStatus addHotels(@RequestBody List<HotelDTO> hotelDTOList)
    {
        try
        {
            hotelService.saveAll(hotelDTOList);
            return HttpStatus.CREATED;
        }
        catch (Exception e)
        {
            e.printStackTrace();
            return HttpStatus.BAD_REQUEST;
        }
    }*/

    @PostMapping
    public ResponseEntity<Response> addHotel(@RequestBody HotelDTO hotelDTO)
    {
        return hotelService.save(hotelDTO);
    }

    @DeleteMapping(path = "/{hotel_name}")
    public ResponseEntity<Response> deleteHotel(@PathVariable("hotel_name") String hotelName) throws CommonException {
        return hotelService.delete(hotelName);
    }

 /*   @DeleteMapping(params = "address")
    public ResponseEntity<Response> deleteHotelByLocation(@RequestParam("address") String location)
    {
        List<Hotel> hotels = hotelRepository.findByAddress(location);
        if(!hotels.isEmpty())
        {
            hotelService.deleteAll(hotels);
            return HttpStatus.OK;
        }
        return HttpStatus.NOT_FOUND;
    }*/

    @PutMapping
    public ResponseEntity<Response> updateHotel(@RequestBody HotelDTO hotelDTO) throws CommonException {
            return hotelService.update(hotelDTO);
    }
}