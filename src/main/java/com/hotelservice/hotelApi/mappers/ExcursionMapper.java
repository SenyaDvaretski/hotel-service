package com.hotelservice.hotelApi.mappers;

import com.hotelservice.hotelApi.DTO.ExcursionDTO;
import com.hotelservice.hotelApi.models.Excursion;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface ExcursionMapper {
    ExcursionMapper INSTANCE = Mappers.getMapper(ExcursionMapper.class);
    ExcursionDTO toDTO(Excursion excursions);

    Excursion toEntity(ExcursionDTO excursionDTO);

    void updateExcursionFromDTO(ExcursionDTO excursionDTO,@MappingTarget Excursion excursion);
}
