package com.hotelservice.hotelApi.repository;

import com.hotelservice.hotelApi.model.Excursion;
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
public interface ExcursionRepository extends JpaRepository<Excursion, UUID> {
    Optional<Excursion> findExcursionByHotelIdAndName(UUID id, String excursionName);
    List<Excursion> findAllExcursionsByHotelId(UUID id);

    @Modifying
    @Query("select ex from excursions ex join ex.tags tag where tag in :tags and ex.hotelId = :id")
    List<Excursion> getExcursionsByHotelIdAndTags(@Param("id") UUID id, @Param("tags") Set<String> tags);

    @Modifying
    @Transactional
    @Query(value = "insert into hotel_service.excursions_tags values(:id, :tag)", nativeQuery = true)
    void addTagToExcursion(@Param("id") UUID hotelID, @Param("tag") String tag);

}
