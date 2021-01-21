package com.crs.microservices.hotelinformationservice.controller;

import com.crs.microservices.hotelinformationservice.model.Address;
import com.crs.microservices.hotelinformationservice.model.Reservation;
import com.crs.microservices.hotelinformationservice.model.AddressImpl;
import com.crs.microservices.hotelinformationservice.model.Hotel;
import com.crs.microservices.hotelinformationservice.model.ReservationImpl;
import com.crs.microservices.hotelinformationservice.model.RoomImpl;
import com.crs.microservices.hotelinformationservice.services.HotelService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.crs.microservices.hotelinformationservice.model.IHotel;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.Arrays;
import java.util.Date;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.BDDMockito.given;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class HotelControllerTest {

    @MockBean
    private HotelService service;

    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext context;

    private Address address = new AddressImpl(1L, "Nagar Pune Road", "KedGaon", "Ahmednagar","414001");
    private IHotel hotel = new Hotel(1L, "Hotel Yash Grand", "0241-2411429",2, address);
    private Reservation reservation = new ReservationImpl(new RoomImpl(),1L, new Date(), new Date(), 1L, "REQUESTED", "KING");

    @Before
    public void setUp() {
        this.mockMvc = MockMvcBuilders
                .webAppContextSetup(context)
                .apply(springSecurity())
                .build();
    }

    @Test
    public void addNewHotel() throws Exception {
        given(service.addNewHotel(any())).willReturn(hotel);
        ObjectMapper mapper =  new ObjectMapper();
        String json =  mapper.writeValueAsString(hotel);
        mockMvc.perform(post("/hotel")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(json)
                        .with(user("Hotel")
                                .password("password")
                                .roles("HOTEL")))
                        .andExpect(status().isCreated());
    }

    @Test
    public void reservationRequest() throws Exception {
        given(service.reservationRequest(anyLong(), any())).willReturn(reservation);
        ObjectMapper mapper = new ObjectMapper();
        String json = mapper.writeValueAsString(reservation);
        mockMvc.perform(post("/hotel/reservation")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .param("hotelId", "1")
                .content(json)
                .with(user("Guest")
                        .password("password")
                        .roles("GUEST")))
                .andExpect(status().isOk());
    }

    @Test
    public void reservationRequest_ForbiddenForHotelRole() throws Exception {
        given(service.reservationRequest(anyLong(), any())).willReturn(reservation);
        ObjectMapper mapper = new ObjectMapper();
        String json = mapper.writeValueAsString(reservation);
        mockMvc.perform(post("/hotel/reservation")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .param("hotelId", "1")
                .content(json)
                .with(user("Hotel")
                        .password("password")
                        .roles("HOTEL")))
                .andExpect(status().isForbidden());
    }

    @Test
    public void cancelReservation() throws Exception {
        given(service.cancelReservation(anyLong(),anyLong())).willReturn(reservation);
        mockMvc.perform(patch("/hotel/cancelReservation")
                        .param("hotelId", "1")
                        .param("reservationId","1")
                        .with(user("Guest")
                            .password("password")
                            .roles("GUEST")))
                        .andExpect(status().isAccepted());

    }

    @Test
    public void getAllReservationsByHotelId() throws Exception {
        given(service.getAllReservationsByHotelId(anyLong())).willReturn(Arrays.asList(reservation));
        mockMvc.perform(get("/hotel/reservationsByHotel")
                .param("hotelId", "1")
                .with(user("Hotel")
                        .password("password")
                        .roles("HOTEL")))
                .andExpect(status().isOk());
    }

    @Test
    public void getReservationByGuest() throws Exception {
        given(service.getReservationByGuestIdPerHotel(anyLong(),anyLong())).willReturn(Arrays.asList(reservation));
        mockMvc.perform(get("/hotel/reservationsByHotel")
                .param("hotelId", "1")
                .param("guestId","1")
                .accept(MediaType.APPLICATION_JSON_VALUE)
                .with(user("Hotel")
                        .password("password")
                        .roles("HOTEL")))
                .andExpect(status().isOk());
    }

    @Test
    public void confirmReservation() throws Exception {
        given(service.confirmReservation(anyLong())).willReturn(reservation);
        mockMvc.perform(patch("/hotel/confirmReservation")
                .param("reservationId", "1")
                .accept(MediaType.APPLICATION_JSON_VALUE)
                .with(user("Guest")
                        .password("password")
                        .roles("GUEST")))
                .andExpect(status().isAccepted());

    }

    @Test
    public void getHotels() {

    }

    @Test
    public void getHotelById() throws Exception {
        given(service.getHotelById(anyLong())).willReturn(hotel);
        mockMvc.perform(get("/hotel")
                .param("hotelId", "1")
                .accept(MediaType.APPLICATION_JSON_VALUE)
                .with(user("Guest")
                        .password("password")
                        .roles("GUEST")))
                .andExpect(status().isOk());

    }

}