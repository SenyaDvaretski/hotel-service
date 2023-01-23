package com.hotelservice.hotelApi.repository;

import com.hotelservice.hotelApi.model.Hotel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface HotelRepository extends JpaRepository<Hotel, UUID> {
    Optional<Hotel> findHotelByName(String hotelName);

    Optional<Hotel> findHotelById(UUID id);

    List<Hotel> findHotelByAddress(String location);
}
