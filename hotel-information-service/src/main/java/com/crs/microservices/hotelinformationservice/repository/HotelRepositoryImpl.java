package com.crs.microservices.hotelinformationservice.repository;

import com.crs.microservices.hotelinformationservice.entity.HotelEntity;
import com.crs.microservices.hotelinformationservice.repository.HotelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;

@Component
public class HotelRepositoryImpl {

    @Autowired
    private HotelRepository hotelRepository;

    public HotelEntity findById(Long id) throws EntityNotFoundException {
        Optional<HotelEntity> hotelEntity = hotelRepository.findById(id);
        return hotelEntity.isPresent() ? hotelEntity.get() :hotelEntity.orElseThrow(() ->new EntityNotFoundException("Hotel with Given ID Not found :"+id));
    }

    public HotelEntity save(HotelEntity hotel){
        return hotelRepository.save(hotel);
    }

}
