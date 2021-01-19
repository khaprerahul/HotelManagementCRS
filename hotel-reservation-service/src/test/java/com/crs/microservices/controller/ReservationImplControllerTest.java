package com.crs.microservices.controller;

import com.crs.microservices.model.Reservation;
import com.crs.microservices.model.CardImpl;
import com.crs.microservices.model.ReservationImpl;
import com.crs.microservices.proxy.model.payment.ICard;
import com.crs.microservices.proxy.model.payment.implementation.Card;
import com.crs.microservices.service.ReservationService;
import com.fasterxml.jackson.databind.ObjectMapper;
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
public class ReservationImplControllerTest {

    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext context;

    @MockBean
    private ReservationService reservationService;

    private ICard card =  new Card("1234567890","12","2024");
    private Reservation reservation = new ReservationImpl(new Date(), new Date(), 1L, 1L, 1L, "REQUEST","SINGLE", new CardImpl(card.getCardNumber(), card.getExpMonth(), card.getExpYear()));
    @Before
    public void setup(){
        this.mockMvc = MockMvcBuilders
                .webAppContextSetup(context)
                .apply(springSecurity())
                .build();
    }

    @Test
    public void getGuestById() {
    }

    @Test
    public void getHotelById() {
    }

    @Test
    public void reservationRequest() throws Exception {
        ObjectMapper mapper =  new ObjectMapper();
        String json =  mapper.writeValueAsString(reservation);
        given(reservationService.requestForReservation(any())).willReturn("Request submitted.");
        mockMvc.perform(post("/reservation/request")
                        .content(json)
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .with(user("Guest")
                            .password("password")
                            .roles("GUEST")))
                .andExpect(status().isOk());
    }

    @Test
    public void confirmReservation() throws Exception {
        ObjectMapper mapper =  new ObjectMapper();
        String json =  mapper.writeValueAsString(reservation);
        given(reservationService.confirmReservation(any())).willReturn(reservation);
        mockMvc.perform(patch("/reservation/confirm")
                .content(json)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .with(user("Guest")
                        .password("password")
                        .roles("GUEST")))
                .andExpect(status().isOk());

    }

    @Test
    public void getReservation() throws Exception {
        given(reservationService.getReservation(anyLong(), anyBoolean())).willReturn(reservation);
        mockMvc.perform(get("/reservation")
                .param("id","1")
                .param("isDetailsRequired","false")
                .accept(MediaType.APPLICATION_JSON_VALUE)
                .with(user("Guest")
                        .password("password")
                        .roles("GUEST")))
                .andExpect(status().isOk());
    }

    @Test
    public void cancelReservation() throws Exception {
        reservation.setState("CANCELLED");
        given(reservationService.cancelReservation(anyLong(), anyDouble())).willReturn(reservation);
        mockMvc.perform(patch("/reservation/cancel")
                        .accept(MediaType.APPLICATION_JSON_VALUE)
                        .param("reservationId","1")
                        .param("amount","500")
                        .with(user("Guest")
                                .password("password")
                                .roles("GUEST"))
                        )
                .andExpect(status().isOk());

    }
}