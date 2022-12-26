package com.hotelservice.hotelApi.repositories;

import com.hotelservice.hotelApi.models.Hotel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface HotelRepository extends JpaRepository<Hotel,Integer> {
    Optional<Hotel> findByName(String hotelName);

    List<Hotel> findByAddress(String location);
}
