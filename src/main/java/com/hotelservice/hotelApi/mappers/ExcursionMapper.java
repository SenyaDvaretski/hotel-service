package com.hotelservice.hotelApi.mappers;

import com.hotelservice.hotelApi.DTO.ExcursionDTO;
import com.hotelservice.hotelApi.models.Excursion;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface ExcursionMapper {
    ExcursionMapper INSTANCE = Mappers.getMapper(ExcursionMapper.class);
    ExcursionDTO toDTO(Excursion excursions);

    @Mapping(source = "enabled", target = "enabled", defaultValue = "true")
    @Mapping(source = "description", target = "description", defaultValue = "No description")
    Excursion toEntity(ExcursionDTO excursionDTO);

    void updateExcursionFromDTO(ExcursionDTO excursionDTO,@MappingTarget Excursion excursion);
}
