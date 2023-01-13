package com.hotelservice.hotelApi.mappers;

import com.hotelservice.hotelApi.DTO.AdditionalServiceDTO;
import com.hotelservice.hotelApi.models.AdditionalService;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring", uses = AdditionalServiceMapper.class)
public interface AdditionalServiceListMapper {
    AdditionalServiceListMapper INSTANCE = Mappers.getMapper(AdditionalServiceListMapper.class);
    List<AdditionalServiceDTO> toDTOList(List<AdditionalService> additionalServices);

    List<AdditionalService> toEntityList(List<AdditionalServiceDTO> additionalServicesDTO);
}

