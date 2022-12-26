package com.hotelservice.hotelApi.controllers;

import com.hotelservice.hotelApi.DTO.RoomDTO;
import com.hotelservice.hotelApi.mappers.RoomMapper;
import com.hotelservice.hotelApi.models.Hotel;
import com.hotelservice.hotelApi.models.Room;
import com.hotelservice.hotelApi.repositories.HotelRepository;
import com.hotelservice.hotelApi.repositories.RoomRepository;
import com.hotelservice.hotelApi.services.RoomService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.webjars.NotFoundException;

import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping(path = "hotels/{hotel_name}/rooms")
public class RoomController {

}
