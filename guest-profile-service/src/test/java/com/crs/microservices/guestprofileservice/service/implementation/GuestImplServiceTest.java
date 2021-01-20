package com.crs.microservices.guestprofileservice.service.implementation;

import com.crs.microservices.guestprofileservice.entity.CardEntity;
import com.crs.microservices.guestprofileservice.entity.GuestEntity;
import com.crs.microservices.guestprofileservice.model.Card;
import com.crs.microservices.guestprofileservice.model.Guest;
import com.crs.microservices.guestprofileservice.model.CardImpl;
import com.crs.microservices.guestprofileservice.model.GuestImpl;
import com.crs.microservices.guestprofileservice.service.GuestService;
import com.crs.microservices.guestprofileservice.repository.GuestRepositoryImpl;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.core.annotation.Order;
import org.springframework.test.context.junit4.SpringRunner;

import javax.persistence.EntityNotFoundException;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;

@RunWith(SpringRunner.class)
@SpringBootTest
public class GuestImplServiceTest {

    @Autowired
    private GuestService guestService;

    @MockBean
    private GuestRepositoryImpl repository;

    Guest guest = new GuestImpl(1L, "Michael Kong","michaelkong@hotmail.com","007878");
    GuestEntity guestEntity =  new GuestEntity(1L, "Michael Kong","michaelkong@hotmail.com", "007878");
    GuestEntity guestEntity1 =  new GuestEntity(2L, "Michael Kong","michaelkong@hotmail.com", "007878");
    @Test
    @Order(1)
    public void addNewGuest() {

        given(repository.save(any())).willReturn(guestEntity);
        Guest resultGuest = guestService.addNewGuest(guest);

        assertEquals(guestEntity.getGuestId(), resultGuest.getGuestId());
        assertEquals(guestEntity.getName(), resultGuest.getName());
        assertEquals(guestEntity.getEmail(), resultGuest.getEmail());
        assertEquals(guestEntity.getContactNumber(), resultGuest.getContactNumber());
    }

    @Test
    @Order(2)
    public void getGuest() {
        given(repository.findById(anyLong())).willReturn(guestEntity);
        Guest resultGuest = guestService.getGuest(1L);
        assertEquals(guestEntity.getGuestId(), resultGuest.getGuestId());
        assertEquals(guestEntity.getName(), resultGuest.getName());
        assertEquals(guestEntity.getEmail(), resultGuest.getEmail());
        assertEquals(guestEntity.getContactNumber(), resultGuest.getContactNumber());
    }

    @Test(expected = EntityNotFoundException.class)
    @Order(3)
    public void getGuest_EntityNotFound() {
        given(repository.findById(anyLong())).willThrow(new EntityNotFoundException("Entity not found"));
        guestService.getGuest(1L);
    }

    @Test
    @Order(4)
    public void addStayByGuest() {
        given(repository.findById(anyLong())).willReturn(guestEntity);
        Guest resultGuest = guestService.addStayByGuest(1L, 1L);
        assertTrue(resultGuest.getReservations().stream().filter(aLong -> aLong.equals(1L)).findFirst().isPresent());
    }

    @Test
    @Order(5)
    public void getGuests() {
        given(repository.findById(1L)).willReturn(guestEntity);
        given(repository.findById(2L)).willReturn(guestEntity1);
        List<Guest> guestList = guestService.getGuests(Arrays.asList(1L, 2L));
        assertTrue(guestList.stream().filter(guest -> guest.getGuestId().equals(1L)).findFirst().isPresent());
        assertTrue(guestList.stream().filter(guest -> guest.getGuestId().equals(2L)).findFirst().isPresent());
    }

}