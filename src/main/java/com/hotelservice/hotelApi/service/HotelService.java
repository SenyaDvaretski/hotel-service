package com.hotelservice.hotelApi.service;

import com.hotelservice.hotelApi.DTO.HotelDTO;
import com.hotelservice.hotelApi.constant.CommonExceptionStatus;
import com.hotelservice.hotelApi.exception.CommonException;
import com.hotelservice.hotelApi.mappers.HotelListMapper;
import com.hotelservice.hotelApi.mappers.HotelMapper;
import com.hotelservice.hotelApi.models.Hotel;
import com.hotelservice.hotelApi.repository.HotelRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
@AllArgsConstructor
public class HotelService {

    private final HotelRepository hotelRepository;
    private final HotelListMapper hotelListMapper;
    private final HotelMapper hotelMapper;


    public ResponseEntity<HotelDTO> addHotel(HotelDTO hotelDTO){
        Hotel hotel = hotelMapper.toEntity(hotelDTO);
        hotel.setId(UUID.randomUUID());
        hotelRepository.save(hotel);
        hotelRepository.flush();
        return new ResponseEntity<>(hotelDTO, HttpStatus.OK);
    }

    public ResponseEntity<HotelDTO> getHotel(String name) throws CommonException {
        Optional<Hotel> hotel = hotelRepository.findByName(name);
        if(hotel.isPresent()){
            return new ResponseEntity<>(hotelMapper.toDTO(hotel.get()), HttpStatus.OK);
        }
        throw new CommonException(CommonExceptionStatus.HOTEL_NOT_FOUND,
                                    "No hotels with this name", HttpStatus.NOT_FOUND);
    }

    public ResponseEntity<HotelDTO> delete(String hotelName) throws CommonException {
        Optional<Hotel> opt_hotel = hotelRepository.findByName(hotelName);
        if(opt_hotel.isPresent()){
            hotelRepository.delete(opt_hotel.get());
            return new ResponseEntity<>(hotelMapper.toDTO(opt_hotel.get()), HttpStatus.OK);
        }
        throw new CommonException(CommonExceptionStatus.HOTEL_NOT_FOUND, "Hotel with such name doesnt exist", HttpStatus.NOT_FOUND);
    }

    public ResponseEntity<HotelDTO> update(HotelDTO hotelDTO) throws CommonException {
        Optional<Hotel> opt_hotel = hotelRepository.findByName(hotelDTO.getName());
        if (opt_hotel.isPresent()){
            hotelMapper.updateHotelFromDto(hotelDTO, opt_hotel.get());
            return new ResponseEntity<>(hotelDTO, HttpStatus.OK);
        }
        throw new CommonException(CommonExceptionStatus.HOTEL_NOT_FOUND, "Hotel with such name doesnt exist", HttpStatus.NOT_FOUND);
    }
}
