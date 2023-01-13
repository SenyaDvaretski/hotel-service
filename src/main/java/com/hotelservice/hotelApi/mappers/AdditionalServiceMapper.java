package com.hotelservice.hotelApi.mappers;

import com.hotelservice.hotelApi.DTO.AdditionalServiceDTO;
import com.hotelservice.hotelApi.models.AdditionalService;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface AdditionalServiceMapper {
    AdditionalServiceMapper INSTANCE = Mappers.getMapper(AdditionalServiceMapper.class);
    AdditionalServiceDTO toDTO(AdditionalService additionalService);

    AdditionalService toEntity(AdditionalServiceDTO additionalServiceDTO);

    void updateAdditionalServiceFromDTO(AdditionalServiceDTO additionalServiceDTO,@MappingTarget AdditionalService additionalService);
}
