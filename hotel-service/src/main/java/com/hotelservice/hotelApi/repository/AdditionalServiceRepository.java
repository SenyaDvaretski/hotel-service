package com.hotelservice.hotelApi.repository;

import com.hotelservice.hotelApi.model.AdditionalService;
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
public interface AdditionalServiceRepository extends JpaRepository<AdditionalService, UUID> {
    Optional<AdditionalService> findByHotelIdAndName(UUID id, String additionalServiceName);
    void deleteAdditionalServiceByHotelIdAndName(UUID hotelId, String name);

    List<AdditionalService> findAllAdditionalServicesByHotelId(UUID hotelID);

    @Modifying
    @Query("select serv from additional_services serv join serv.tags tag where tag in :tags and serv.hotelId = :id")
    List<AdditionalService> getAdditionalServicesByHotelIdAndTags(@Param("id") UUID hotelId, @Param("tags") Set<String> tags);

    @Modifying
    @Transactional
    @Query(value = "insert into hotel_service.additional_services_tags values(:id, :tag)", nativeQuery = true)
    void addTagToAdditionalService(@Param("id") UUID hotelID, @Param("tag") String tag);
}
