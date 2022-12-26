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
import java.util.Objects;
import java.util.Optional;

@Service
@AllArgsConstructor
public class HotelService {

    public final HotelRepository hotelRepository;

    private HotelListMapper hotelListMapper;
    private HotelMapper hotelMapper;

    public void save(HotelDTO hotelDTO){
        hotelRepository.save(hotelMapper.toEntity(hotelDTO));
    }

    public List<Hotel> getAll(){
        return hotelRepository.findAll();
    }

    public void saveAll(List<HotelDTO> hotelDTOList) {
        hotelRepository.saveAll(hotelListMapper.toEntityList(hotelDTOList));
    }

    public void delete(Hotel hotel) {
        hotelRepository.delete(hotel);
    }

    public void deleteAll(List<Hotel> hotels) {
        hotelRepository.deleteAll(hotels);
    }

    public HttpStatus update(HotelDTO hotelDTO)
    {
        Optional<Hotel> opt_hotel = hotelRepository.findByName(hotelDTO.getName());
        if(opt_hotel.isPresent())
        {
            Hotel hotelDb = opt_hotel.get();
            if(Objects.nonNull(hotelDTO.getAddress()) && !"".equals(hotelDTO.getAddress()))
            {
                hotelDb.setAddress(hotelDTO.getAddress());
            }
            if(Objects.nonNull(hotelDTO.getName()) && !"".equals(hotelDTO.getName()))
            {
                hotelDb.setName(hotelDTO.getName());
            }
            if(Objects.nonNull(hotelDTO.getType()) && !"".equals(hotelDTO.getType()))
            {
                hotelDb.setType(hotelDTO.getType());
            }
            if(Objects.nonNull(hotelDTO.getDescription()) && !"".equals(hotelDTO.getDescription()))
            {
                hotelDb.setDescription(hotelDTO.getDescription());
            }
            if(Objects.nonNull(hotelDTO.getRooms()))
            {
                hotelDb.setRooms(hotelDTO.getRooms());
            }
            if(Objects.nonNull(hotelDTO.getServices()) )
            {
                hotelDb.setServices(hotelDTO.getServices());
            }
            if(Objects.nonNull(hotelDTO.getExcursions()))
            {
                hotelDb.setExcursions(hotelDTO.getExcursions());
            }

            hotelRepository.save(hotelDb);
            return HttpStatus.OK;
        }
        else
        {
            return HttpStatus.NOT_FOUND;
        }

    }

    public HttpStatus renameHotel(String hotelName, String newHotelName)
    {
        Optional<Hotel> opt_hotel = hotelRepository.findByName(hotelName);
        if(opt_hotel.isPresent())
        {
            hotelRepository.save(opt_hotel.get().setName(newHotelName));
            return HttpStatus.OK;
        }
        return HttpStatus.NOT_FOUND;
    }
}
