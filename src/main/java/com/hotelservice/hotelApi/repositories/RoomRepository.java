package com.hotelservice.hotelApi.repositories;

import com.hotelservice.hotelApi.models.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface RoomRepository extends JpaRepository<Room, UUID> {
    Optional<Room> findByHotelIdAndNumber(UUID hotel_id, int number);
    List<Room> findByHotelIdAndAvailableTrue(UUID hotel_id);

    List<Room> findByHotelIdAndAvailableFalse(UUID hotel_id);

}
