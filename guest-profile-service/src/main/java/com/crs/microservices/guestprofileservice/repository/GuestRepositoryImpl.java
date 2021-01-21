package com.crs.microservices.guestprofileservice.repository;

import com.crs.microservices.guestprofileservice.repository.GuestRepository;
import com.crs.microservices.guestprofileservice.entity.GuestEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.persistence.EntityNotFoundException;
import java.util.Optional;

@Component
public class GuestRepositoryImpl {

    @Autowired
    private GuestRepository repository;
    public GuestEntity save(GuestEntity guestEntity){
        return repository.save(guestEntity);
    }

    public GuestEntity findById(Long id) throws EntityNotFoundException {
        Optional<GuestEntity> guestOption = repository.findById(id);
        return guestOption.isPresent() ? guestOption.get() : guestOption.orElseThrow(()-> new EntityNotFoundException("Guest not found."));
    }
}
