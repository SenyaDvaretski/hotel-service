package com.hotelservice.hotelApi.exception.ControllerAdvice;

import com.hotelservice.hotelApi.constant.CommonExceptionStatus;
import com.hotelservice.hotelApi.exception.CommonException;
import com.hotelservice.hotelApi.mappers.ErrorResponseMapper;
import com.hotelservice.hotelApi.response.ErrorResponse;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalTime;

@ControllerAdvice
@AllArgsConstructor
public class HotelServiceControllerAdvice {

    ErrorResponseMapper errorResponseMapper;

    @ExceptionHandler(CommonException.class)
    public ResponseEntity<ErrorResponse> handleCommonException(CommonException ex){
        ex.printStackTrace();
        ErrorResponse errorResponse = errorResponseMapper.toErrorResponse(ex);
        return new ResponseEntity<>(errorResponse, ex.getHttpStatus());
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleUnexpectedException(Exception ex){
        ex.printStackTrace();
        ErrorResponse errorResponse = ErrorResponse.builder()
                .exceptionStatus(CommonExceptionStatus.INTERNAL_SERVER_ERROR)
                .message("Внутренняя ошибка сервера")
                .localTime(LocalTime.now()).build();
        return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
