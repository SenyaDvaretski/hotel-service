package com.hotelservice.hotelApi.repositories;

import com.hotelservice.hotelApi.models.AdditionalService;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdditionalServiceRepository extends JpaRepository<AdditionalService,Integer> {
}
