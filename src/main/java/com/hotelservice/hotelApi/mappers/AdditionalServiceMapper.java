package com.hotelservice.hotelApi.mappers;

import com.hotelservice.hotelApi.DTO.AdditionalServiceDTO;
import com.hotelservice.hotelApi.services.AdditionalServiceService;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AdditionalServiceMapper {
    AdditionalServiceDTO toDTO(AdditionalServiceService additionalService);

    AdditionalServiceService toEntity(AdditionalServiceDTO additionalServiceDTO);
}
