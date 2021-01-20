package com.crs.microservices.guestprofileservice.repository;


import com.crs.microservices.guestprofileservice.entity.GuestEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GuestRepository extends CrudRepository<GuestEntity, Long> {

}
