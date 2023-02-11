package com.hotelservice.hotelApi.exception;

import com.hotelservice.hotelApi.constant.CommonExceptionStatus;
import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
public class CommonException extends Exception{
    private final CommonExceptionStatus exceptionStatus;
    private final String message;
    private final HttpStatus httpStatus;
}
