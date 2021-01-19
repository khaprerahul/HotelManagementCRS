package com.crs.microservices.repository;

import com.crs.microservices.entity.ReservationDTO;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IReservationRepository extends CrudRepository<ReservationDTO, Long> {
}
