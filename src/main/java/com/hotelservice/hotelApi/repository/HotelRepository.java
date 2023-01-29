package com.hotelservice.hotelApi.repository;

import com.hotelservice.hotelApi.model.Hotel;
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
public interface HotelRepository extends JpaRepository<Hotel, UUID> {
    Optional<Hotel> findHotelByName(String hotelName);

    Optional<Hotel> findHotelById(UUID id);

    List<Hotel> findAllHotelsByAddress(String location);

    @Modifying
    @Query("select hotel from hotels hotel join hotel.tags tag where tag in :tags")
    List<Hotel> getAllHotelsByTags(@Param("tags") Set<String> tags);

    @Modifying
    @Transactional
    @Query(value = "insert into hotel_service.hotels_tags values(:id, :tag)", nativeQuery = true)
    void addTagToHotel(@Param("id") UUID hotelID, @Param("tag") String tag);

}
