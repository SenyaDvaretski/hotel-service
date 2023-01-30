package com.hotelservice.hotelApi.repositories;

import com.hotelservice.hotelApi.models.Excursion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ExcursionRepository extends JpaRepository<Excursion,Integer> {
}
