package com.hotelservice.hotelApi.repository;

import com.hotelservice.hotelApi.models.Hotel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface HotelRepository extends JpaRepository<Hotel, UUID> {
    Optional<Hotel> findByName(String hotelName);

    Optional<Hotel> findById(UUID id);

    List<Hotel> findByAddress(String location);
}
