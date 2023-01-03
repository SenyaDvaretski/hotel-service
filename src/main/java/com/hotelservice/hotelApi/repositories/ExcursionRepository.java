package com.hotelservice.hotelApi.repositories;

import com.hotelservice.hotelApi.models.Excursion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ExcursionRepository extends JpaRepository<Excursion, UUID> {
}
