package com.hotelservice.hotelApi.repository;

import com.hotelservice.hotelApi.model.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

@Repository
public interface RoomRepository extends JpaRepository<Room, UUID> {
    Optional<Room> findByHotelIdAndNumber(UUID hotel_id, Integer number);
    List<Room> findByHotelIdAndAvailable(UUID hotel_id, Boolean available);

    @Modifying
    @Query("select room from rooms room join room.tags tag where tag in :tags and room.hotelId = :id")
    List<Room> getRoomsByHotelIdAndTags(@Param("id") UUID hotelId, @Param("tags") Set<String> tags);

    @Modifying
    @Transactional
    @Query(value = "insert into hotel_service.rooms_tags values(:id, :tag)", nativeQuery = true)
    void addTagToRoom(@Param("id") UUID hotelID, @Param("tag") String tag);
}
