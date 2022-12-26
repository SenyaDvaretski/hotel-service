package com.hotelservice.hotelApi.mappers;

import com.hotelservice.hotelApi.DTO.ExcursionDTO;
import com.hotelservice.hotelApi.models.Excursion;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring", uses = ExcursionMapper.class)
public interface ExcursionListMapper {
    List<ExcursionDTO> toDTOList(List<Excursion> excursions);

    List<Excursion> toEntityList(List<ExcursionDTO> excursionsDTO);
}
