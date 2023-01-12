package com.hotelservice.hotelApi.repository;

import com.hotelservice.hotelApi.models.AdditionalService;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface AdditionalServiceRepository extends JpaRepository<AdditionalService, UUID> {
    Optional<AdditionalService> findByHotelIdAndName(UUID id, String additionalServiceName);

//    void deleteAdditionalServiceByNameAnd todo

    //todo use @Modifing https://www.baeldung.com/spring-data-jpa-modifying-annotation
}
