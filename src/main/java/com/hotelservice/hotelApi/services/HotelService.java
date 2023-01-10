package com.hotelservice.hotelApi.services;

import com.hotelservice.hotelApi.DTO.HotelDTO;
import com.hotelservice.hotelApi.mappers.HotelListMapper;
import com.hotelservice.hotelApi.mappers.HotelMapper;
import com.hotelservice.hotelApi.models.Hotel;
import com.hotelservice.hotelApi.repositories.HotelRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@AllArgsConstructor
public class HotelService {

    public final HotelRepository hotelRepository;
    private HotelListMapper hotelListMapper;
    private HotelMapper hotelMapper;

    public void save(HotelDTO hotelDTO){
        Hotel hotel = hotelMapper.toEntity(hotelDTO);
        hotel.setId(UUID.randomUUID());
        hotelRepository.save(hotel);
    }

    public List<HotelDTO> getAll(){
        return hotelListMapper.toDTOList(hotelRepository.findAll()) ;
    }

    public HotelDTO getHotel(String name){
        Optional<Hotel> hotel = hotelRepository.findByName(name);
        if(hotel.isPresent()){
            return hotelMapper.toDTO(hotel.get());
        }else{
            return null;
        }
    }

    public void saveAll(List<HotelDTO> hotelDTOList) {
        List<Hotel> hotelList = hotelListMapper.toEntityList(hotelDTOList);
        hotelList.forEach(hotel -> hotel.setId(UUID.randomUUID()));
        hotelRepository.saveAll(hotelList);
    }

    public void delete(Hotel hotel) {
        hotelRepository.delete(hotel);
    }

    public void deleteAll(List<Hotel> hotels) {
        hotelRepository.deleteAll(hotels);
    }

    public HttpStatus update(HotelDTO hotelDTO)
    {
        Optional<Hotel> hotel = hotelRepository.findByName(hotelDTO.getName());
        if (hotel.isEmpty()){
            return HttpStatus.NOT_FOUND;
        }
        hotelMapper.updateHotelFromDto(hotelDTO, hotel.get());
        return HttpStatus.OK;
    }
}
