package com.hotelservice.hotelApi.mappers;

import com.hotelservice.hotelApi.exception.CommonException;
import com.hotelservice.hotelApi.response.ErrorResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring", imports = java.time.LocalTime.class)
public interface ErrorResponseMapper {
    ErrorResponseMapper INSTANCE = Mappers.getMapper(ErrorResponseMapper.class);

    String localTime = "java(LocalTime.now())";
    @Mapping(target = "localTime", expression = localTime)
    ErrorResponse toErrorResponse(CommonException commonException);
}
