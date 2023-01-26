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
import java.util.Set;
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
        Optional<Hotel> optHotel = hotelRepository.findHotelByName(name);
        if(optHotel.isPresent()){
            return new ResponseEntity<>(hotelMapper.toDTO(optHotel.get()), HttpStatus.OK);
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
        Optional<Hotel> optHotel = hotelRepository.findHotelByName(hotelName);
        if(optHotel.isPresent()){
            hotelRepository.delete(optHotel.get());
            return new ResponseEntity<>(hotelMapper.toDTO(optHotel.get()), HttpStatus.OK);
        }
        throw new CommonException(CommonExceptionStatus.HOTEL_NOT_FOUND, "Hotel with such name doesnt exist", HttpStatus.NOT_FOUND);
    }

    public ResponseEntity<HotelDTO> update(HotelDTO hotelDTO) throws CommonException {
       // isValidHotel(hotelDTO);
        Optional<Hotel> optHotel = hotelRepository.findHotelByName(hotelDTO.getName());
        if (optHotel.isPresent()){
            hotelRepository.delete(optHotel.get());
            hotelMapper.updateHotelFromDto(hotelDTO, optHotel.get());
            hotelRepository.save(optHotel.get());
            return new ResponseEntity<>(hotelDTO, HttpStatus.OK);
        }
        throw new CommonException(CommonExceptionStatus.HOTEL_NOT_FOUND, "Hotel with such name doesnt exist", HttpStatus.NOT_FOUND);
    }

    public ResponseEntity<HotelDTO> addTagToHotel(String hotelName, String tag) throws CommonException {
        Optional<Hotel> optHotel = hotelRepository.findHotelByName(hotelName);
        if (optHotel.isPresent()){
            hotelRepository.addTagToHotel(optHotel.get().getId(), tag);
            HotelDTO hotelDTO = hotelMapper.toDTO(hotelRepository.findHotelByName(hotelName).get());
            return new ResponseEntity<>(hotelDTO, HttpStatus.OK);
        }
        throw new CommonException(CommonExceptionStatus.HOTEL_NOT_FOUND, "Cannot add tag: hotel with such name doesnt exist", HttpStatus.NOT_FOUND);
    }

    public ResponseEntity<List<HotelDTO>> getAllHotelsByTags(Set<String> tags) throws CommonException {
        List<Hotel> hotels = hotelRepository.getHotelsByTags(tags);
        if(hotels.isEmpty()){
            throw new CommonException(CommonExceptionStatus.NO_HOTELS_FOUND, "No hotels with this tags found", HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(hotelListMapper.toDTOList(hotels), HttpStatus.OK);
    }

}
