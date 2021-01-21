package com.crs.microservices.hotelinformationservice.services;


import com.crs.microservices.hotelinformationservice.entity.AddressEntity;
import com.crs.microservices.hotelinformationservice.entity.HotelEntity;
import com.crs.microservices.hotelinformationservice.entity.ReservationEntity;
import com.crs.microservices.hotelinformationservice.entity.RoomEntity;
import com.crs.microservices.hotelinformationservice.model.*;
import com.crs.microservices.hotelinformationservice.repository.HotelRepositoryImpl;
import com.crs.microservices.hotelinformationservice.repository.ReservationRepositoryImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;


@RunWith(SpringRunner.class)
@SpringBootTest
public class HotelServiceTest {

    @Autowired
    private HotelService hotelService;

    @MockBean
    private HotelRepositoryImpl hotelRepository;

    @MockBean
    private ReservationRepositoryImpl reservationRepository;

    private Address address = new AddressImpl(1L, "Nagar Pune Road", "Kedgaon", "Ahmednagar","414001");
    private IHotel hotel = new Hotel(1L, "Hotel Yash Grand", "0241-2411429",2, address);
    private Reservation reservation = new ReservationImpl(new RoomImpl(),1L, new Date(), new Date(), 1L, "REQUESTED", "KING");

    private AddressEntity addressEntity =  new AddressEntity(1L, "Lane no1", "Hanuman Nagar", "Pune","412308");
    private HotelEntity hotelEntity =  new HotelEntity(1L, "City Inn", "1234567890",3, addressEntity);
    private RoomEntity roomEntity =  new RoomEntity(102, 1000, RoomType.KING);
    private ReservationEntity reservationEntity =  new ReservationEntity(new RoomEntity(),1L, new Date(), new Date(), 1L, "REQUEST", "KING");
    private ReservationEntity reservationEntity2 =  new ReservationEntity(new RoomEntity(),2L, new Date(), new Date(), 1L, "REQUEST", "KING");

    @Test
    public void addNewHotel() {
        given(hotelRepository.save(any())).willReturn(hotelEntity);
        IHotel hotel1 = hotelService.addNewHotel(hotel);
        assertEquals(hotel1.getHotelId(), hotelEntity.getHotelId());
    }

    @Test
    public void confirmReservation() {
        given(reservationRepository.findReservationById(anyLong())).willReturn(reservationEntity);
        Reservation reservation = hotelService.confirmReservation(1L);
        assertTrue(reservation.getState().equals("CONFIRMED"));
    }

    @Test
    public void getReservationByGuestIdPerHotel() {

        hotelEntity.getReservations().add(reservationEntity);
        hotelEntity.getReservations().add(reservationEntity2);
        given(hotelRepository.findById(anyLong())).willReturn(hotelEntity);
        List<Reservation> reservation = hotelService.getReservationByGuestIdPerHotel(1L, 1L);

        assertFalse(reservation.stream().filter(r -> !r.getGuestId().equals(1L)).findFirst().isPresent());

    }

    @Test
    public void cancelingReservationTest() {
        given(reservationRepository.findReservationById(anyLong())).willReturn(reservationEntity);
        Reservation reservation = hotelService.cancelReservation(1L, 1L);
        assertTrue(reservation.getReservationId().equals(1L));
        assertTrue(reservation.getState().equals("CANCELLED"));
    }

    @Test(expected = EntityNotFoundException.class)
    public void cancelReservationIfEntityIsNotFound() {
        given(reservationRepository.findReservationById(anyLong())).willThrow(new EntityNotFoundException("Entity Not found"));
        Reservation reservation = hotelService.cancelReservation(1L, 1L);
        assertTrue(reservation.getReservationId().equals(1L));
        assertTrue(reservation.getState().equals("CANCELLED"));
    }

    @Test
    public void reservationRequestedTest() {
        hotelEntity.setReservations(new ArrayList<>());
        hotelEntity.getRooms().add(roomEntity);
        given(hotelRepository.findById(anyLong())).willReturn(hotelEntity);
        Reservation reservation = hotelService.reservationRequest(1L, this.reservation);
        assertTrue(reservation.getState().equals("REQUESTED"));
    }

    @Test
    public void getListOfReservationsForaHotel() {
        given((hotelRepository.findById(anyLong()))).willReturn(hotelEntity);
        hotelEntity.getReservations().add(reservationEntity2);
        hotelEntity.getReservations().add(reservationEntity);
        List<Reservation> reservations = hotelService.getAllReservationsByHotelId(1L);

        assertEquals(reservations.size(), 2);
    }

    @Test
    public void getHotelByHotelId() {
        given(hotelRepository.findById(anyLong())).willReturn(hotelEntity);
        IHotel iHotel = hotelService.getHotelById(1L);
        assertTrue(iHotel.getHotelId().equals(1L));
    }

    @Test(expected = EntityNotFoundException.class)
    public void getHotelByIdIfNotFound() {
        given(hotelRepository.findById(anyLong())).willThrow(new EntityNotFoundException("Entity Not found"));
        hotelService.getHotelById(1L);
    }

}