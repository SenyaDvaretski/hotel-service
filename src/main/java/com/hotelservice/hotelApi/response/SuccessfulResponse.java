package com.hotelservice.hotelApi.response;

import lombok.Data;

import java.time.LocalTime;

@Data
public class SuccessfulResponse<BaseDTO> implements Response{
    private final BaseDTO result;
    private final LocalTime localTime;
}
