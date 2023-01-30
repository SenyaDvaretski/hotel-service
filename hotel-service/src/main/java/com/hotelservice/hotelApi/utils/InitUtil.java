package com.hotelservice.hotelApi.utils;

import com.hotelservice.hotelApi.models.Hotel;
import com.hotelservice.hotelApi.services.HotelService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
@RequiredArgsConstructor
public class InitUtil implements CommandLineRunner {

    private final HotelService hotelService;
    @Override
    public void run(String... args) throws Exception {
        System.out.println("run");

        List<Hotel> hotels = new ArrayList<>(
                Arrays.asList()
        );
    }
}
