package com.crs.microservices.hotelinformationservice.repository;

import com.crs.microservices.hotelinformationservice.entity.HotelEntity;
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
        Optional<HotelEntity> hotelDTO = hotelRepository.findById(id);
        return hotelDTO.isPresent() ? hotelDTO.get() :hotelDTO.orElseThrow(() ->new EntityNotFoundException("Hotel not found for id :"+id));
    }

    public HotelEntity save(HotelEntity hotel){
        return hotelRepository.save(hotel);
    }

    public List<HotelEntity> getAllHotels(){
        return hotelRepository.findAll();
    }
}
