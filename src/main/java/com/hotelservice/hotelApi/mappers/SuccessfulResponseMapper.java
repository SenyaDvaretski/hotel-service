package com.hotelservice.hotelApi.mappers;

import com.hotelservice.hotelApi.DTO.AdditionalServiceDTO;
import com.hotelservice.hotelApi.DTO.ExcursionDTO;
import com.hotelservice.hotelApi.DTO.HotelDTO;
import com.hotelservice.hotelApi.DTO.RoomDTO;
import com.hotelservice.hotelApi.response.SuccessfulResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface SuccessfulResponseMapper {
    SuccessfulResponseMapper INSTANCE = Mappers.getMapper(SuccessfulResponseMapper.class);

    String localTime = "java(LocalTime.now())";
    @Mapping(target = "localTime", expression = localTime)
    SuccessfulResponse<HotelDTO> toSuccessfulResponse(HotelDTO result);

    @Mapping(target = "localTime", expression = localTime)
    SuccessfulResponse<RoomDTO> toSuccessfulResponse(RoomDTO result);

    @Mapping(target = "localTime", expression = localTime)
    SuccessfulResponse<ExcursionDTO> toSuccessfulResponse(ExcursionDTO result);

    @Mapping(target = "localTime", expression = localTime)
    SuccessfulResponse<AdditionalServiceDTO> toSuccessfulResponse(AdditionalServiceDTO result);
}
