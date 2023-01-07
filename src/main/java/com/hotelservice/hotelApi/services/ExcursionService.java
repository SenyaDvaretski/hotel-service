package com.hotelservice.hotelApi.services;

import com.hotelservice.hotelApi.DTO.ExcursionDTO;
import com.hotelservice.hotelApi.mappers.ExcursionListMapper;
import com.hotelservice.hotelApi.mappers.ExcursionMapper;
import com.hotelservice.hotelApi.models.Excursion;
import com.hotelservice.hotelApi.models.Hotel;
import com.hotelservice.hotelApi.repositories.ExcursionRepository;
import com.hotelservice.hotelApi.repositories.HotelRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ExcursionService {
    public final ExcursionRepository excursionRepository;
    public final HotelRepository hotelRepository;

    private ExcursionListMapper excursionListMapper;
    private ExcursionMapper excursionMapper;

    public HttpStatus addExcursion(String hotelName, ExcursionDTO excursionDTO){
        Optional<Hotel> hotel = hotelRepository.findByName(hotelName);
        Excursion excursion = excursionMapper.toEntity(excursionDTO);
        if(hotel.isPresent()){
            excursion.setHotelId(hotel.get().getId());
            excursionRepository.save(excursion);
            return HttpStatus.CREATED;
        }else return HttpStatus.NOT_FOUND;
    }

    public List<ExcursionDTO> getAllExcursions(String hotelName) throws Exception {
        Optional<Hotel> hotel = hotelRepository.findByName(hotelName);
        if(hotel.isPresent()){
            return excursionListMapper.toDTOList(hotel.get().getExcursions());
        }
        throw new Exception("not found");
    }

    public HttpStatus deleteExcursion(String hotelName, String excursionName){
        Optional<Hotel> hotel = hotelRepository.findByName(hotelName);
        if(hotel.isPresent()){
            Optional<Excursion> excursion = excursionRepository.
                    findByHotelIdAndName(hotel.get().getId(), excursionName);
            if(excursion.isPresent()){
                excursionRepository.delete(excursion.get());
                return HttpStatus.OK;
            }
            return HttpStatus.NOT_FOUND;
        }
        return HttpStatus.NOT_FOUND;
    }

    public HttpStatus updateExcursion(String hotelName, ExcursionDTO excursionDTO)
    {
        Optional<Hotel> hotel = hotelRepository.findByName(hotelName);
        if(hotel.isPresent())
        {
            Optional<Excursion> room = excursionRepository.
                    findByHotelIdAndName(hotel.get().getId(), excursionDTO.getName());
            if(room.isPresent())
            {
                excursionMapper.updateExcursionFromDTO(excursionDTO, room.get());
                return HttpStatus.OK;
            }
        }
        return HttpStatus.NOT_FOUND;
    }

    public HttpStatus setEnabled(String hotelName,
                                 String excursionName,
                                 boolean isEnabled)
    {
        Optional<Hotel> opt_hotel = hotelRepository.findByName(hotelName);
        if(opt_hotel.isPresent()){
            Optional<Excursion>  opt_excursion = excursionRepository.
                                        findByHotelIdAndName(opt_hotel.get().getId(), excursionName);
            if(opt_excursion.isPresent()){
                excursionRepository.save(opt_excursion.get().setEnabled(isEnabled));
                return HttpStatus.OK;
            }
        }
        return HttpStatus.NOT_FOUND;
    }

    public void deleteAll(List<Excursion> excursions) {
        excursionRepository.deleteAll(excursions);
    }
}
