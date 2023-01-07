package com.hotelservice.hotelApi.controllers;

import com.hotelservice.hotelApi.DTO.HotelDTO;
import com.hotelservice.hotelApi.models.Hotel;
import com.hotelservice.hotelApi.repositories.HotelRepository;
import com.hotelservice.hotelApi.services.HotelService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.webjars.NotFoundException;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/hotels")
public class HotelController {

    private final HotelRepository hotelRepository;
    private final HotelService hotelService;

    HotelController(HotelRepository hotelRepository, HotelService hotelService)
    {
        this.hotelRepository = hotelRepository;
        this.hotelService = hotelService;
    }

    @GetMapping
    public Iterable<Hotel> getHotels(){
        return hotelRepository.findAll();
    }

    @GetMapping(path = "/{hotel_name}")
    public Hotel getHotel(@PathVariable("hotel_name") String hotelName)
    {
        Optional<Hotel> opt_hotel = hotelRepository.findByName(hotelName);

        if(opt_hotel.isPresent()) { return opt_hotel.get(); }
        throw new NotFoundException("No required hotels");
    }


    @PostMapping
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
    }

    @PostMapping(path = "/{hotel_name}")
    public HttpStatus addHotel(@RequestBody HotelDTO hotelDTO)
    {
        try
        {
            hotelService.save(hotelDTO);
            return HttpStatus.OK;
        }
        catch (Exception e)
        {
            e.printStackTrace();
            return HttpStatus.BAD_REQUEST;
        }
    }

    @DeleteMapping(path = "/{hotel_name}")
    public HttpStatus deleteHotel(@PathVariable("hotel_name") String hotelName)
    {
        Optional<Hotel> opt_hotel = hotelRepository.findByName(hotelName);
        if(opt_hotel.isPresent())
        {
            hotelService.delete(opt_hotel.get());
            return HttpStatus.OK;
        }
        return HttpStatus.NOT_FOUND;

    }

    @DeleteMapping(params = "address")
    public HttpStatus deleteHotelByLocation(@RequestParam("address") String location)
    {
        List<Hotel> hotels = hotelRepository.findByAddress(location);
        if(!hotels.isEmpty())
        {
            hotelService.deleteAll(hotels);
            return HttpStatus.OK;
        }
        return HttpStatus.NOT_FOUND;
    }

    @PutMapping
    public HttpStatus updateHotel(@RequestBody HotelDTO hotelDTO){
        try{
            return hotelService.update(hotelDTO);
        }catch (Exception e){
            return HttpStatus.CONFLICT;
        }

    }
}