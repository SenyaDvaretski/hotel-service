package com.hotelservice.hotelApi.mappers;

import com.hotelservice.hotelApi.DTO.AdditionalServiceDTO;
import com.hotelservice.hotelApi.models.AdditionalService;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface AdditionalServiceMapper {
    AdditionalServiceDTO toDTO(AdditionalService additionalService);

    AdditionalService toEntity(AdditionalServiceDTO additionalServiceDTO);

    void updateAdditionalServiceFromDTO(AdditionalServiceDTO additionalServiceDTO,@MappingTarget AdditionalService additionalService);
}
