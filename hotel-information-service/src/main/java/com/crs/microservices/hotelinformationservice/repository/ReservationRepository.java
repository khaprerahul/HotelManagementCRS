package com.crs.microservices.hotelinformationservice.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import com.crs.microservices.hotelinformationservice.dto.ReservationDTO;

@Repository
public interface ReservationRepository extends CrudRepository<ReservationDTO, Long> {
}
