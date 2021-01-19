package com.crs.microservices.repository.implementation;

import com.crs.microservices.entity.ReservationDTO;
import com.crs.microservices.repository.IReservationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityNotFoundException;
import java.util.Optional;

@Repository
public class ReservationRepository {

    @Autowired
    private IReservationRepository IReservationRepository;

    public ReservationDTO save(ReservationDTO input){
        return  IReservationRepository.save(input);
    }

    public ReservationDTO getReservationById(Long id){
        Optional<ReservationDTO> reservationDTO = IReservationRepository.findById(id);
        return reservationDTO.isPresent() ? reservationDTO.get(): reservationDTO.orElseThrow(() ->new EntityNotFoundException("Reservation not found for id "+id));
    }
}
