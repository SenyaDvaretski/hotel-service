package com.hotelservice.hotelApi.repository;

import com.hotelservice.hotelApi.model.Excursion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface ExcursionRepository extends JpaRepository<Excursion, UUID> {
    Optional<Excursion> findExcursionByHotelIdAndName(UUID id, String excursionName);
    List<Excursion> findAllExcursionsByHotelId(UUID id);

}
