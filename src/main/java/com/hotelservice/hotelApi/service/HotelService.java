package com.hotelservice.hotelApi.service;

import com.hotelservice.hotelApi.DTO.HotelDTO;
import com.hotelservice.hotelApi.constant.CommonExceptionStatus;
import com.hotelservice.hotelApi.exception.CommonException;
import com.hotelservice.hotelApi.mappers.ErrorResponseMapper;
import com.hotelservice.hotelApi.mappers.HotelListMapper;
import com.hotelservice.hotelApi.mappers.HotelMapper;
import com.hotelservice.hotelApi.mappers.SuccessfulResponseMapper;
import com.hotelservice.hotelApi.models.Hotel;
import com.hotelservice.hotelApi.repository.HotelRepository;
import com.hotelservice.hotelApi.response.Response;
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

    private final SuccessfulResponseMapper successfulResponseMapper;
    private final ErrorResponseMapper errorResponseMapper;

    public ResponseEntity<Response> save(HotelDTO hotelDTO){
        Hotel hotel = hotelMapper.toEntity(hotelDTO);
        hotel.setId(UUID.randomUUID());
        hotelRepository.save(hotel);
        return new ResponseEntity<>(successfulResponseMapper.toSuccessfulResponse(hotelDTO), HttpStatus.OK);
    }

    public ResponseEntity<Response> getHotel(String name) throws CommonException {
        Optional<Hotel> hotel = hotelRepository.findByName(name);
        if(hotel.isPresent()){
            return new ResponseEntity<>(successfulResponseMapper
                            .toSuccessfulResponse(hotelMapper.toDTO(hotel.get())), HttpStatus.OK);
        }
        throw new CommonException(CommonExceptionStatus.HOTEL_NOT_FOUND,
                                    "No hotels with this name", HttpStatus.NOT_FOUND);
    }

    public ResponseEntity<Response> delete(String hotelName) throws CommonException {
        Optional<Hotel> opt_hotel = hotelRepository.findByName(hotelName);
        if(opt_hotel.isPresent()){
            hotelRepository.delete(opt_hotel.get());
            return new ResponseEntity<>(successfulResponseMapper
                                .toSuccessfulResponse(hotelMapper.toDTO(opt_hotel.get())),
                                    HttpStatus.OK);
        }
        throw new CommonException(CommonExceptionStatus.HOTEL_NOT_FOUND, "Hotel with such name doesnt exist", HttpStatus.NOT_FOUND);
    }

    public ResponseEntity<Response> update(HotelDTO hotelDTO) throws CommonException {
        Optional<Hotel> opt_hotel = hotelRepository.findByName(hotelDTO.getName());
        if (opt_hotel.isPresent()){
            hotelMapper.updateHotelFromDto(hotelDTO, opt_hotel.get());
            return new ResponseEntity<>(successfulResponseMapper
                    .toSuccessfulResponse(hotelDTO), HttpStatus.OK);
        }
        throw new CommonException(CommonExceptionStatus.HOTEL_NOT_FOUND, "Hotel with such name doesnt exist", HttpStatus.NOT_FOUND);
    }
}
