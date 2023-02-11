package com.hotelservice.hotelApi.service;

import com.hotelservice.hotelApi.DTO.ExcursionDTO;
import com.hotelservice.hotelApi.constant.CommonExceptionStatus;
import com.hotelservice.hotelApi.exception.CommonException;
import com.hotelservice.hotelApi.mappers.ExcursionListMapper;
import com.hotelservice.hotelApi.mappers.ExcursionMapper;
import com.hotelservice.hotelApi.model.Excursion;
import com.hotelservice.hotelApi.model.Hotel;
import com.hotelservice.hotelApi.repository.ExcursionRepository;
import com.hotelservice.hotelApi.repository.HotelRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

@Service
@AllArgsConstructor
public class ExcursionService {
    public final ExcursionRepository excursionRepository;
    public final HotelRepository hotelRepository;

    private ExcursionListMapper excursionListMapper;
    private ExcursionMapper excursionMapper;

    public ExcursionDTO addExcursion(String hotelName, ExcursionDTO excursionDTO) throws CommonException {
        Optional<Hotel> optHotel = hotelRepository.findHotelByName(hotelName);
        Excursion excursion = excursionMapper.toEntity(excursionDTO);
        if(optHotel.isPresent()){
            excursion.setId(UUID.randomUUID());
            excursion.setHotelId(optHotel.get().getId());
            excursionRepository.save(excursion);
            return excursionDTO;
        }
        throw new CommonException(CommonExceptionStatus.HOTEL_NOT_FOUND,
                                    "Unable to add new excursion: no hotels with this name found",
                                    HttpStatus.CONFLICT);
    }

    public List<ExcursionDTO> getAllExcursions(String hotelName) throws CommonException {
        Optional<Hotel> optHotel = hotelRepository.findHotelByName(hotelName);
        if(optHotel.isPresent()){
            List<Excursion> excursionsList = excursionRepository.findAllExcursionsByHotelId(optHotel.get().getId());
            if(!excursionsList.isEmpty()) {
                return excursionListMapper.toDTOList(excursionsList);
            }
            throw new CommonException(CommonExceptionStatus.EXCURSIONS_NOT_FOUND,
                    "No excursions found",
                     HttpStatus.NOT_FOUND);
        }
        throw new CommonException(CommonExceptionStatus.HOTEL_NOT_FOUND,
                "Unable to find all excursions: no hotels with this name found",
                HttpStatus.NOT_FOUND);
    }

    public ExcursionDTO getExcursion(String hotelName, String excursionName) throws CommonException {
        Optional<Hotel> optHotel = hotelRepository.findHotelByName(hotelName);
        if(optHotel.isPresent()){
            Optional<Excursion> optExcursion = excursionRepository.
                    findExcursionByHotelIdAndName(optHotel.get().getId(), excursionName);
            if(optExcursion.isPresent()){
                return excursionMapper.toDTO(optExcursion.get());
            }
            throw new CommonException(CommonExceptionStatus.ROOM_NOT_FOUND,
                    "Unable to get excursion: room with this number is not found",
                    HttpStatus.NOT_FOUND);
        }
        throw new CommonException(CommonExceptionStatus.HOTEL_NOT_FOUND,
                "Unable to get excursion: hotel with this name is not found",
                HttpStatus.NOT_FOUND);
    }

    public ExcursionDTO deleteExcursion(String hotelName, String excursionName) throws CommonException {
        Optional<Hotel> hotel = hotelRepository.findHotelByName(hotelName);
        if(hotel.isPresent()){
            Optional<Excursion> excursion = excursionRepository.
                    findExcursionByHotelIdAndName(hotel.get().getId(), excursionName);
            if(excursion.isPresent()){
                excursionRepository.delete(excursion.get());
                return excursionMapper.toDTO(excursion.get());
            }
            throw new CommonException(CommonExceptionStatus.EXCURSIONS_NOT_FOUND,
                    "Unable to delete excursion: excursion with ths name not found",
                    HttpStatus.CONFLICT);
        }
        throw new CommonException(CommonExceptionStatus.HOTEL_NOT_FOUND,
                "Unable to delete excursion: no hotels with this name found",
                HttpStatus.NOT_FOUND);
    }

    public ExcursionDTO updateExcursion(String hotelName, ExcursionDTO excursionDTO) throws CommonException {
        Optional<Hotel> optHotel = hotelRepository.findHotelByName(hotelName);
        if(optHotel.isPresent())
        {
            Optional<Excursion> optExcursion = excursionRepository.
                    findExcursionByHotelIdAndName(optHotel.get().getId(), excursionDTO.getName());
            if(optExcursion.isPresent())
            {
                excursionRepository.delete(optExcursion.get());
                excursionMapper.updateExcursionFromDTO(excursionDTO, optExcursion.get());
                excursionRepository.save(optExcursion.get());
                return excursionMapper.toDTO(optExcursion.get());
            }
            throw new CommonException(CommonExceptionStatus.EXCURSIONS_NOT_FOUND,
                    "Unable to update excursion: excursion with ths name not found",
                    HttpStatus.CONFLICT);
        }
        throw new CommonException(CommonExceptionStatus.HOTEL_NOT_FOUND,
                "Unable to update excursion: no hotels with this name found",
                HttpStatus.NOT_FOUND);
    }

    public ExcursionDTO setEnabled(String hotelName,
                                     String excursionName,
                                     boolean isEnabled) throws CommonException {

        Optional<Hotel> optHotel = hotelRepository.findHotelByName(hotelName);
        if(optHotel.isPresent()){
            Optional<Excursion>  optExcursion = excursionRepository.
                                        findExcursionByHotelIdAndName(optHotel.get().getId(), excursionName);
            if(optExcursion.isPresent()){
                excursionRepository.delete(optExcursion.get());
                optExcursion.get().setEnabled(isEnabled);
                excursionRepository.save(optExcursion.get());
                return excursionMapper.toDTO(optExcursion.get());
            }
            throw new CommonException(CommonExceptionStatus.EXCURSIONS_NOT_FOUND,
                    "Unable to set enabled excursion: excursion with ths name not found",
                    HttpStatus.CONFLICT);
        }
        throw new CommonException(CommonExceptionStatus.HOTEL_NOT_FOUND,
                "Unable to set enable excursion: no hotels with this name found",
                HttpStatus.NOT_FOUND);
    }

    public ExcursionDTO addTagToExcursion(String hotelName, String excursionName, String tag) throws CommonException {
        Optional<Hotel> optHotel = hotelRepository.findHotelByName(hotelName);
        if (optHotel.isPresent()){
            Optional<Excursion> optExcursion = excursionRepository.findExcursionByHotelIdAndName(optHotel.get().getId(), excursionName);
            if(optExcursion.isPresent()) {
                excursionRepository.addTagToExcursion(optExcursion.get().getId(), tag);
                return excursionMapper.toDTO(excursionRepository.findExcursionByHotelIdAndName(optHotel.get().getId(), excursionName).get());
            }
            throw new CommonException(CommonExceptionStatus.EXCURSIONS_NOT_FOUND,
                    "Cannot add tag to excursion: excursion with this name doesn't exist",
                    HttpStatus.NOT_FOUND);
        }
        throw new CommonException(CommonExceptionStatus.HOTEL_NOT_FOUND,
                "Cannot add tag to excursion: hotel with this name doesn't exist",
                HttpStatus.NOT_FOUND);
    }

    public List<ExcursionDTO> getAllExcursionsByHotelNameAndTags(String hotelName, Set<String> tags) throws CommonException {
        Optional<Hotel> optHotel = hotelRepository.findHotelByName(hotelName);
        if(optHotel.isPresent()){
            List<Excursion> excursionList = excursionRepository.getExcursionsByHotelIdAndTags(optHotel.get().getId(), tags);
            if(!excursionList.isEmpty()){
                return excursionListMapper.toDTOList(excursionList);
            }
            throw new CommonException(CommonExceptionStatus.NO_EXCURSIONS_FOUND,
                    "No excursions with this tags found",
                    HttpStatus.NOT_FOUND);
        }
        throw new CommonException(CommonExceptionStatus.HOTEL_NOT_FOUND,
                "No hotels with this name found",
                HttpStatus.NOT_FOUND);
    }
}
