package com.hotelservice.hotelApi.response;

import com.hotelservice.hotelApi.constant.CommonExceptionStatus;
import lombok.Builder;
import lombok.Data;

import java.time.LocalTime;

@Data
@Builder
public class ErrorResponse implements Response{
    private final CommonExceptionStatus exceptionStatus;
    private final String message;
    private final LocalTime localTime;
}
