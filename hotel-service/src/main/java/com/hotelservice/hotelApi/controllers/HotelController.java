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

}