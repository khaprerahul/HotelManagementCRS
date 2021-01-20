package com.crs.microservices.guestprofileservice.controller;

import com.crs.microservices.guestprofileservice.model.Card;
import com.crs.microservices.guestprofileservice.model.Guest;
import com.crs.microservices.guestprofileservice.model.CardImpl;
import com.crs.microservices.guestprofileservice.service.GuestService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.crs.microservices.guestprofileservice.model.GuestImpl;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import javax.persistence.EntityNotFoundException;

import java.util.Arrays;

import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class GuestImplControllerTest {

    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext context;

    @MockBean
    private GuestService guestService;

    @Before
    public void setup(){
        this.mockMvc = MockMvcBuilders
                .webAppContextSetup(context)
                .apply(springSecurity())
                .build();
    }

    private Guest guest = new GuestImpl(1L, "Michael Kong","michaelkong@hotmail.com","007878");
    @Test
    public void getGuest_ShouldReturnGuest() throws Exception {
        given(guestService.getGuest(1L)).willReturn(guest);
        mockMvc.perform(MockMvcRequestBuilders.get("/guest/1")
                .with(user("Guest")
                        .password("password")
                        .roles("GUEST")))
                .andExpect(status().isOk())
                .andExpect(jsonPath("name").value("Michael Kong"))
        .andExpect(jsonPath("email").value("michaelkong@hotmail.com"))
        .andExpect(jsonPath("contactNumber").value("007878"));


    }

    @Test
    public void getGuest_EntityNotFoundException() throws Exception {
        given(guestService.getGuest(anyLong())).willThrow(new EntityNotFoundException("Entity Not found"));
        mockMvc.perform(MockMvcRequestBuilders.get("/guest/1")
                .with(user("Guest")
                        .password("password")
                        .roles("GUEST")))
                .andExpect(status().isNotFound());

    }

    @Test
    public void addNewStay() throws Exception {
        Guest guest = new GuestImpl(1L, "Michael Kong","michaelkong@hotmail.com","007878");
        given(guestService.addStayByGuest(anyLong(), any())).willReturn(guest);
        mockMvc.perform(MockMvcRequestBuilders.patch("/guest/addNewStay")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .param("guestId", "1")
                .param("reservationId","1")
                .with(user("Guest")
                        .password("password")
                        .roles("GUEST")))
                .andExpect(status().isOk());
                //.andExpect(content().string("Stay added successfully."));
    }

    @Test
    public void addNewGuest() throws Exception {
        Guest guest = new GuestImpl(1L, "Michael Kong","michaelkong@hotmail.com","007878");
        given(guestService.addNewGuest(any())).willReturn(guest);
        ObjectMapper mapper =  new ObjectMapper();
        String json = mapper.writeValueAsString(guest);
        mockMvc.perform(post("/guest/create")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(json)
                .with(user("Guest")
                        .password("password")
                        .roles("GUEST")))
                .andExpect(status().isCreated());

    }

    @Test
    public void getGuests() throws Exception {
        Guest guest = new GuestImpl(1L, "Michael Kong","michaelkong@hotmail.com","007878");
        given(guestService.getGuests(any())).willReturn(Arrays.asList(guest));

        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get("/guest/getGuests")
                .param("guestId", "1")
                .param("guestId", "2")
                .with(user("Guest")
                        .password("password")
                        .roles("GUEST")))
                .andExpect(status().isOk())
                .andReturn();

        assertTrue(mvcResult.getResponse().getContentAsString().contains("Michael Kong"));
    }

}