package com.crs.microservices.hotelinformationservice.repository;

import com.crs.microservices.hotelinformationservice.entity.ReservationEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IReservationRepository extends CrudRepository<ReservationEntity, Long> {
}
