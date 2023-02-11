package com.hotelservice.hotelApi.mappers;

import com.hotelservice.hotelApi.DTO.ExcursionDTO;
import com.hotelservice.hotelApi.model.Excursion;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring", uses = ExcursionMapper.class)
public interface ExcursionListMapper {
    ExcursionListMapper INSTANCE = Mappers.getMapper(ExcursionListMapper.class);
    List<ExcursionDTO> toDTOList(List<Excursion> excursions);

    List<Excursion> toEntityList(List<ExcursionDTO> excursionsDTO);
}
