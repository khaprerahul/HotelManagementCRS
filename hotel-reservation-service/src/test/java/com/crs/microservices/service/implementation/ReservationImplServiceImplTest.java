package com.crs.microservices.service.implementation;


import com.crs.microservices.entity.CardDTO;
import com.crs.microservices.entity.ReservationDTO;
import com.crs.microservices.model.Card;
import com.crs.microservices.model.Reservation;
import com.crs.microservices.model.CardImpl;
import com.crs.microservices.model.ReservationImpl;
import com.crs.microservices.proxy.IGuestInformationProxy;
import com.crs.microservices.proxy.IHotelInformationProxy;
import com.crs.microservices.proxy.model.guest.Guest;
import com.crs.microservices.proxy.model.guest.GuestImpl;
import com.crs.microservices.proxy.model.hotel.Address;
import com.crs.microservices.proxy.model.hotel.Hotel;
import com.crs.microservices.proxy.model.hotel.AddressImpl;
import com.crs.microservices.proxy.model.hotel.HotelImpl;
import com.crs.microservices.repository.implementation.ReservationRepository;
import com.crs.microservices.service.ReservationService;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import javax.persistence.EntityNotFoundException;

import java.util.Date;

import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ReservationImplServiceImplTest {

    @MockBean
    private ReservationRepository reservationRepository;

    @MockBean
    private IHotelInformationProxy hotelProxy;

    @MockBean
    private IGuestInformationProxy guestProxy;

    @Autowired
    private ReservationService reservationService;

    private Guest guest =  new GuestImpl(1l, "Rahul","khaprerahul0311@gmail.com","9860738239");
    private Address address = new AddressImpl(1L, "Nagar Pune Road", "Kedgaon", "Ahmednagar");
    private Hotel hotel = new HotelImpl(1L, "Hotel Yash Grand", "0241-2411942", 3, address);;
    private Card card =  new CardImpl("54979898XXXX","10","2029");
    private Reservation reservation = new ReservationImpl(new Date(), new Date(), 1L, 1L, 1L, "REQUEST","SINGLE", card);
    private ReservationDTO reservationDTO =  new ReservationDTO(new Date(), new Date(), 1L, 1L, 1L, "REQUEST", new CardDTO(card.getCardNumber(), card.getExpMonth(), card.getExpYear()));


    @Test
    public void getGuestById() {
        given(guestProxy.getGuest(anyLong())).willReturn(guest);
        Guest guest = reservationService.getGuestById(1L);
        assertTrue(guest.getGuestId().equals(1L));
    }

    @Test(expected = EntityNotFoundException.class)
    public void getGuestById_EntityNotFound() {
        given(guestProxy.getGuest(anyLong())).willThrow(new EntityNotFoundException("Guest not found"));
        reservationService.getGuestById(1L);
    }

    @Test
    public void getHotelById() {
        given(hotelProxy.getHotelById(anyLong())).willReturn(hotel);
        Hotel hotel = reservationService.getHotelById(1L);
        assertTrue(hotel.getHotelId().equals(1L));
        assertTrue(hotel.getAddress().getAddressId().equals(1L));
    }

    @Test
    public void requestForReservation() {
        given(reservationRepository.save(any())).willReturn(reservationDTO);
        given(hotelProxy.reservationRequest(any(), anyLong())).willReturn("Request submitted.");
        reservationService.requestForReservation(reservation);
        verify(hotelProxy).reservationRequest(any(), anyLong());
    }

    @Test
    public void confirmReservation() {
        given(hotelProxy.confirmReservation(anyLong())).willReturn("SUCCESS");
        given(guestProxy.addStayByGuest(anyLong(), anyLong())).willReturn(ResponseEntity.ok("SUCCESS"));
        given(reservationRepository.getReservationById(anyLong())).willReturn(reservationDTO);
        Reservation reservation = reservationService.confirmReservation(this.reservation);
        verify(hotelProxy).confirmReservation(anyLong());
        verify(guestProxy).addStayByGuest(anyLong(), anyLong());
        verify(reservationRepository).getReservationById(anyLong());

        assertTrue(reservation.getState().equals("CONFIRMED"));
    }

    @Test
    public void getReservation() {
        given(reservationRepository.getReservationById(anyLong())).willReturn(reservationDTO);
        reservationService.getReservation(1L, false);
        verify(hotelProxy,times(0)).getHotelById(anyLong());
        verify(guestProxy, times(0)).getGuest(anyLong());
    }

    @Test
    public void getReservation_GetDetails() {
        given(reservationRepository.getReservationById(anyLong())).willReturn(reservationDTO);
        given(guestProxy.getGuest(anyLong())).willReturn(guest);
        given(hotelProxy.getHotelById(anyLong())).willReturn(hotel);
        reservationService.getReservation(1L, true);
        verify(hotelProxy).getHotelById(anyLong());
        verify(guestProxy).getGuest(anyLong());
    }

    @Test
    public void doPaymentFallBack() {
    }

    @Test
    public void cancelReservation() throws Exception {
        CardDTO cardDTO =  new CardDTO(card.getCardNumber(), card.getExpMonth(), card.getExpYear());
        reservationDTO.setCard(cardDTO);
        given(reservationRepository.getReservationById(anyLong())).willReturn(reservationDTO);
        given(hotelProxy.cancelReservation(anyLong(), anyLong())).willReturn(ResponseEntity.accepted().build());

        reservationService.cancelReservation(1L, 500);

        verify(reservationRepository).getReservationById(anyLong());
        verify(hotelProxy).cancelReservation(anyLong(), anyLong());
    }

}