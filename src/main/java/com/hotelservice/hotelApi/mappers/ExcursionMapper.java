package com.hotelservice.hotelApi.mappers;

import com.hotelservice.hotelApi.DTO.ExcursionDTO;
import com.hotelservice.hotelApi.models.Excursion;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ExcursionMapper {
    ExcursionDTO toDTO(Excursion excursions);

    Excursion toEntity(ExcursionDTO excursionDTO);
}
