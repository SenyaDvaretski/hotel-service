package com.hotelservice.hotelApi.service;

import com.hotelservice.hotelApi.DTO.HotelDTO;
import com.hotelservice.hotelApi.constant.CommonExceptionStatus;
import com.hotelservice.hotelApi.exception.CommonException;
import com.hotelservice.hotelApi.mappers.HotelListMapper;
import com.hotelservice.hotelApi.mappers.HotelMapper;
import com.hotelservice.hotelApi.model.Hotel;
import com.hotelservice.hotelApi.repository.HotelRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@AllArgsConstructor
public class HotelService {

    private final HotelRepository hotelRepository;
    private final HotelListMapper hotelListMapper;
    private final HotelMapper hotelMapper;

    public ResponseEntity<HotelDTO> addHotel(HotelDTO hotelDTO) {
       // isValidHotel(hotelDTO);
        Hotel hotel = hotelMapper.toEntity(hotelDTO);
        hotel.setId(UUID.randomUUID());
        hotelRepository.save(hotel);
        hotelRepository.flush();
        return new ResponseEntity<>(hotelDTO, HttpStatus.OK);
    }

    public ResponseEntity<HotelDTO> getHotel(String name) throws CommonException {
        Optional<Hotel> hotel = hotelRepository.findHotelByName(name);
        if(hotel.isPresent()){
            return new ResponseEntity<>(hotelMapper.toDTO(hotel.get()), HttpStatus.OK);
        }
        throw new CommonException(CommonExceptionStatus.HOTEL_NOT_FOUND,
                                    "No hotels with this name", HttpStatus.NOT_FOUND);
    }

    public ResponseEntity<List<HotelDTO>> getAllHotels() throws CommonException {
        List<Hotel> hotels = hotelRepository.findAll();
        if(!hotels.isEmpty()){
            return new ResponseEntity<>(hotelListMapper.toDTOList(hotels), HttpStatus.OK);
        }
        throw new CommonException(CommonExceptionStatus.NO_HOTELS_FOUND,
                "No hotels found", HttpStatus.NOT_FOUND);
    }

    public ResponseEntity<List<HotelDTO>> getAllHotelsByAddress(String location) throws CommonException {
        List<Hotel> hotels = hotelRepository.findHotelByAddress(location);
        if(!hotels.isEmpty()){
            return new ResponseEntity<>(hotelListMapper.toDTOList(hotels), HttpStatus.OK);
        }
        throw new CommonException(CommonExceptionStatus.NO_HOTELS_FOUND,
                "No hotels with this address found", HttpStatus.NOT_FOUND);
    }

    public ResponseEntity<HotelDTO> delete(String hotelName) throws CommonException {
        Optional<Hotel> opt_hotel = hotelRepository.findHotelByName(hotelName);
        if(opt_hotel.isPresent()){
            hotelRepository.delete(opt_hotel.get());
            return new ResponseEntity<>(hotelMapper.toDTO(opt_hotel.get()), HttpStatus.OK);
        }
        throw new CommonException(CommonExceptionStatus.HOTEL_NOT_FOUND, "Hotel with such name doesnt exist", HttpStatus.NOT_FOUND);
    }

    public ResponseEntity<HotelDTO> update(HotelDTO hotelDTO) throws CommonException {
       // isValidHotel(hotelDTO);
        Optional<Hotel> opt_hotel = hotelRepository.findHotelByName(hotelDTO.getName());
        if (opt_hotel.isPresent()){
            hotelMapper.updateHotelFromDto(hotelDTO, opt_hotel.get());
            return new ResponseEntity<>(hotelDTO, HttpStatus.OK);
        }
        throw new CommonException(CommonExceptionStatus.HOTEL_NOT_FOUND, "Hotel with such name doesnt exist", HttpStatus.NOT_FOUND);
    }
}
