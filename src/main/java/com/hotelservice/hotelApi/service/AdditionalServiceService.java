package com.hotelservice.hotelApi.service;

import com.hotelservice.hotelApi.DTO.AdditionalServiceDTO;
import com.hotelservice.hotelApi.constant.CommonExceptionStatus;
import com.hotelservice.hotelApi.exception.CommonException;
import com.hotelservice.hotelApi.mappers.AdditionalServiceListMapper;
import com.hotelservice.hotelApi.mappers.AdditionalServiceMapper;
import com.hotelservice.hotelApi.model.AdditionalService;
import com.hotelservice.hotelApi.model.Hotel;
import com.hotelservice.hotelApi.repository.AdditionalServiceRepository;
import com.hotelservice.hotelApi.repository.HotelRepository;
import com.hotelservice.hotelApi.repository.RoomRepository;
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
    private final RoomRepository roomRepository;

    public AdditionalServiceDTO addAdditionalService(String hotelName,
                                           AdditionalServiceDTO additionalServiceDTO) throws CommonException {
        Optional<Hotel> hotel = hotelRepository.findHotelByName(hotelName);
        AdditionalService additionalService = additionalServiceMapper.
                                                toEntity(additionalServiceDTO);
        if(hotel.isPresent()){
            additionalService.setId(UUID.randomUUID());
            additionalService.setHotelId(hotel.get().getId());
            additionalServiceRepository.save(additionalService);
            return additionalServiceDTO;
        }
        throw new CommonException(CommonExceptionStatus.HOTEL_NOT_FOUND,
                "Unable to add additional service: hotel is not found",
                                  HttpStatus.NOT_FOUND);
    }

    public List<AdditionalServiceDTO> getAllAdditionalServices(String hotelName) throws Exception {
        Optional<Hotel> optHotel = hotelRepository.findHotelByName(hotelName);
        if(optHotel.isPresent()){
            List<AdditionalService> additionalServiceList = additionalServiceRepository.findAllAdditionalServicesByHotelId(optHotel.get().getId());
            if(!additionalServiceList.isEmpty()){
                return additionalServiceListMapper.toDTOList(additionalServiceList);
            }
            throw new CommonException(CommonExceptionStatus.NO_ADDITIONAL_SERVICE_FOUND,
                    "No additional services found", HttpStatus.NOT_FOUND);
        }
        throw new CommonException(CommonExceptionStatus.HOTEL_NOT_FOUND,
                "Unable to get get all rooms: hotel with this name is not found",
                HttpStatus.NOT_FOUND);
    }

    public AdditionalServiceDTO getAdditionalService(String hotelName, String additionalServiceName) throws Exception {
        Optional<Hotel> optHotel = hotelRepository.findHotelByName(hotelName);
        if(optHotel.isPresent()){
            Optional<AdditionalService> optAdditionalService = additionalServiceRepository.
                            findByHotelIdAndName(optHotel.get().getId(), additionalServiceName);
            if(optAdditionalService.isPresent()){
                return additionalServiceMapper.toDTO(optAdditionalService.get());
            }
            throw new CommonException(CommonExceptionStatus.ADDITIONAL_SERVICE_NOT_FOUND,
                    "Unable to add additional service: additional service is not found",
                    HttpStatus.NOT_FOUND);
        }
        throw new CommonException(CommonExceptionStatus.HOTEL_NOT_FOUND,
                "Unable to add additional service: hotel is not found",
                HttpStatus.NOT_FOUND);
    }

    public AdditionalServiceDTO deleteAdditionalService(String hotelName, String additionalServiceName) throws CommonException {
        Optional<Hotel> hotel = hotelRepository.findHotelByName(hotelName);
        if(hotel.isPresent()){
            Optional<AdditionalService> additionalService = additionalServiceRepository.
                    findByHotelIdAndName(hotel.get().getId(), additionalServiceName);
            if(additionalService.isPresent()){
                additionalServiceRepository.deleteAdditionalServiceByHotelIdAndName(hotel.get().getId(), additionalServiceName);
                return additionalServiceMapper.toDTO(additionalService.get());
            }
            throw new CommonException(CommonExceptionStatus.ADDITIONAL_SERVICE_NOT_FOUND,
                                      "Unable to delete additional service: additional servise is not found",
                                        HttpStatus.NOT_FOUND);
        }
        throw new CommonException(CommonExceptionStatus.HOTEL_NOT_FOUND,
                "Unable to delete additional service: hotel is not found",
                HttpStatus.NOT_FOUND);
    }

    public AdditionalServiceDTO updateAdditionalService(String hotelName, AdditionalServiceDTO additionalServiceDTO) throws CommonException {
        Optional<Hotel> hotel = hotelRepository.findHotelByName(hotelName);
        if(hotel.isPresent())
        {
            Optional<AdditionalService> optAdditionalService = additionalServiceRepository.
                    findByHotelIdAndName(hotel.get().getId(), additionalServiceDTO.getName());
            if(optAdditionalService.isPresent())
            {
                additionalServiceRepository.deleteAdditionalServiceByHotelIdAndName(optAdditionalService.get().getHotelId(),
                                                                                    additionalServiceDTO.getName());
                additionalServiceMapper.updateAdditionalServiceFromDTO(additionalServiceDTO, optAdditionalService.get());

                additionalServiceRepository.save(optAdditionalService.get());
                return additionalServiceMapper.toDTO(optAdditionalService.get());
            }
            throw new CommonException(CommonExceptionStatus.ADDITIONAL_SERVICE_NOT_FOUND,
                    "Unable to update additional service: additional service is not found",
                    HttpStatus.CONFLICT);
        }
        throw new CommonException(CommonExceptionStatus.HOTEL_NOT_FOUND,
                "Unable to update additional service: hotel is not found",
                HttpStatus.CONFLICT);
    }

    public AdditionalServiceDTO setEnabled(String hotelName,
                                 String additionalServiceName,
                                 Boolean isEnabled) throws CommonException {
        Optional<Hotel> optHotel = hotelRepository.findHotelByName(hotelName);
        if(optHotel.isPresent()){
            Optional<AdditionalService>  optAdditionalService = additionalServiceRepository.
                    findByHotelIdAndName(optHotel.get().getId(), additionalServiceName);
            if(optAdditionalService.isPresent()){
                additionalServiceRepository.deleteAdditionalServiceByHotelIdAndName(optHotel.get().getId(), additionalServiceName);
                additionalServiceRepository.save(optAdditionalService.get().setEnabled(isEnabled));
                return additionalServiceMapper.toDTO(optAdditionalService.get());
            }
            throw new CommonException(CommonExceptionStatus.ADDITIONAL_SERVICE_NOT_FOUND,
                    "Unable to update additional service: additional service is not found",
                    HttpStatus.CONFLICT);
        }
        throw new CommonException(CommonExceptionStatus.HOTEL_NOT_FOUND,
                "Unable to update additional service: hotel is not found",
                HttpStatus.CONFLICT);
    }
}
