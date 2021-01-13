package com.crs.microservices.guestprofileservice.repository;

import org.springframework.data.repository.CrudRepository;

import com.crs.microservices.guestprofileservice.dto.GuestDTO;

public interface GuestRepository extends CrudRepository<GuestDTO, Long> {

}
