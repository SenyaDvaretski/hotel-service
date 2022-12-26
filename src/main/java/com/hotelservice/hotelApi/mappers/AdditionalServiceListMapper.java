package com.hotelservice.hotelApi.mappers;

import com.hotelservice.hotelApi.DTO.AdditionalServiceDTO;
import com.hotelservice.hotelApi.models.AdditionalService;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring", uses = AdditionalServiceMapper.class)
public interface AdditionalServiceListMapper {
    List<AdditionalServiceDTO> toDTOList(List<AdditionalService> additionalServices);

    List<AdditionalService> toEntityList(List<AdditionalServiceDTO> additionalServicesDTO);
}

