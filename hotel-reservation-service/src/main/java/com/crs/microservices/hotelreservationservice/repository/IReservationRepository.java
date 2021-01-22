package com.crs.microservices.hotelreservationservice.repository;

import com.crs.microservices.hotelreservationservice.entity.ReservationEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IReservationRepository extends CrudRepository<ReservationEntity, Long> {
}
