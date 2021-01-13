package com.crs.microservices.hotelinformationservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.crs.microservices.hotelinformationservice.dto.HotelDTO;

@Repository
public interface HotelRepository extends JpaRepository<HotelDTO, Long> {

}
