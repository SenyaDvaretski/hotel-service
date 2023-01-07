package com.hotelservice.hotelApi.repositories;

import com.hotelservice.hotelApi.models.AdditionalService;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface AdditionalServiceRepository extends JpaRepository<AdditionalService, UUID> {
    Optional<AdditionalService> findByHotelIdAndName(UUID id, String additionalServiceName);
}
