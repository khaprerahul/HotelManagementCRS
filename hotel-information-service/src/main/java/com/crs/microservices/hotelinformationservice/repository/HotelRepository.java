package com.crs.microservices.hotelinformationservice.repository;

import com.crs.microservices.hotelinformationservice.entity.HotelEntity;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HotelRepository extends JpaRepository<HotelEntity, Long> {

    /*@Query("FROM HOTEL WHERE address_id in (SELECT ADDRESS_ID FROM ADDRESS WHERE CITY = ?1)")
    List<IHotel> findByCity(String city);*/
}
