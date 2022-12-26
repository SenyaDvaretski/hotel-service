package com.hotelservice.hotelApi.services;

import com.hotelservice.hotelApi.DTO.ExcursionDTO;
import com.hotelservice.hotelApi.mappers.ExcursionListMapper;
import com.hotelservice.hotelApi.mappers.ExcursionMapper;
import com.hotelservice.hotelApi.models.Excursion;
import com.hotelservice.hotelApi.repositories.ExcursionRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ExcursionService {
    public final ExcursionRepository excursionRepository;

    private ExcursionListMapper excursionListMapper;
    private ExcursionMapper excursionMapper;

    public void save(Excursion fruitEntity){
        excursionRepository.save(fruitEntity);
    }

    public List<Excursion> getAll(){
        return excursionRepository.findAll();
    }

    public void saveAll(List<ExcursionDTO> excursionDtoList) {
        excursionRepository.saveAll(excursionListMapper.toEntityList(excursionDtoList));
    }

    public void delete(Excursion excursion) {
        excursionRepository.delete(excursion);
    }

    public void deleteAll(List<Excursion> excursions) {
        excursionRepository.deleteAll(excursions);
    }
}
