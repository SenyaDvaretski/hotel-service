package com.hotelservice.hotelApi.services;

import com.hotelservice.hotelApi.DTO.AdditionalServiceDTO;
import com.hotelservice.hotelApi.mappers.AdditionalServiceListMapper;
import com.hotelservice.hotelApi.mappers.AdditionalServiceMapper;
import com.hotelservice.hotelApi.models.AdditionalService;
import com.hotelservice.hotelApi.repositories.AdditionalServiceRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class AdditionalServiceService {
    public final AdditionalServiceRepository additionalServiceRepository;

    private AdditionalServiceListMapper additionalServiceListMapper;
    private AdditionalServiceMapper additionalServiceMapper;

    public void save(AdditionalService fruitEntity){
        additionalServiceRepository.save(fruitEntity);
    }

    public List<AdditionalService> getAll(){
        return additionalServiceRepository.findAll();
    }

    public void saveAll(List<AdditionalServiceDTO> additionalServiceDTOS) {
        additionalServiceRepository.saveAll(additionalServiceListMapper.toEntityList(additionalServiceDTOS));
    }

    public void delete(AdditionalService additionalService) {
        additionalServiceRepository.delete(additionalService);
    }

    public void deleteAll(List<AdditionalService> additionalServices) {
        additionalServiceRepository.deleteAll(additionalServices);
    }
}
