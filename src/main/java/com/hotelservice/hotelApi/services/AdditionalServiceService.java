package com.hotelservice.hotelApi.services;

import com.hotelservice.hotelApi.DTO.AdditionalServiceDTO;
import com.hotelservice.hotelApi.mappers.AdditionalServiceListMapper;
import com.hotelservice.hotelApi.mappers.AdditionalServiceMapper;
import com.hotelservice.hotelApi.models.AdditionalService;
import com.hotelservice.hotelApi.models.Hotel;
import com.hotelservice.hotelApi.repositories.AdditionalServiceRepository;
import com.hotelservice.hotelApi.repositories.HotelRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@AllArgsConstructor
public class AdditionalServiceService {
    public final AdditionalServiceRepository additionalServiceRepository;
    public final HotelRepository hotelRepository;

    private AdditionalServiceListMapper additionalServiceListMapper;
    private AdditionalServiceMapper additionalServiceMapper;

    public HttpStatus addAdditionalService(String hotelName,
                                           AdditionalServiceDTO additionalServiceDTO)
    {
        Optional<Hotel> hotel = hotelRepository.findByName(hotelName);
        AdditionalService additionalService = additionalServiceMapper.
                                                toEntity(additionalServiceDTO);
        if(hotel.isPresent()){
            additionalService.setId(UUID.randomUUID());
            additionalService.setHotelId(hotel.get().getId());
            additionalServiceRepository.save(additionalService);
            return HttpStatus.CREATED;
        }else return HttpStatus.NOT_FOUND;
    }

    public List<AdditionalServiceDTO> getAllAdditionalServices(String hotelName) throws Exception {
        Optional<Hotel> hotel = hotelRepository.findByName(hotelName);
        if(hotel.isPresent()){
            return additionalServiceListMapper.toDTOList(hotel.get().getAdditionalServices());
        }
        return null;
    }

    public HttpStatus deleteAdditionalService(String hotelName, String additionalServiceName){
        Optional<Hotel> hotel = hotelRepository.findByName(hotelName);
        if(hotel.isPresent()){
            Optional<AdditionalService> additionalService = additionalServiceRepository.
                    findByHotelIdAndName(hotel.get().getId(), additionalServiceName);
            if(additionalService.isPresent()){
                additionalServiceRepository.delete(additionalService.get());
                return HttpStatus.OK;
            }
            return HttpStatus.NOT_FOUND;
        }
        return HttpStatus.NOT_FOUND;
    }

    public HttpStatus update(String hotelName, AdditionalServiceDTO additionalServiceDTO)
    {
        Optional<Hotel> hotel = hotelRepository.findByName(hotelName);
        if(hotel.isPresent())
        {
            Optional<AdditionalService> room = additionalServiceRepository.
                    findByHotelIdAndName(hotel.get().getId(), additionalServiceDTO.getName());
            if(room.isPresent())
            {
                additionalServiceMapper.updateAdditionalServiceFromDTO(additionalServiceDTO, room.get());
                return HttpStatus.OK;
            }
        }
        return HttpStatus.NOT_FOUND;
    }

    public HttpStatus setEnabled(String hotelName,
                                 String additionalServiceName)
    {
        Optional<Hotel> opt_hotel = hotelRepository.findByName(hotelName);
        if(opt_hotel.isPresent()){
            Optional<AdditionalService>  opt_additionalService = additionalServiceRepository.
                    findByHotelIdAndName(opt_hotel.get().getId(), additionalServiceName);
            if(opt_additionalService.isPresent()){
                additionalServiceRepository.save(opt_additionalService.get().
                                                    setEnabled(!opt_additionalService.get().isEnabled()));
                return HttpStatus.OK;
            }
        }
        return HttpStatus.NOT_FOUND;
    }

    public void deleteAll(List<AdditionalService> additionalServices) {
        additionalServiceRepository.deleteAll(additionalServices);
    }
}
