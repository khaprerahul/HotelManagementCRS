package com.crs.microservices.guestprofileservice.repository.implementation;

import com.crs.microservices.guestprofileservice.entity.GuestEntity;
import com.crs.microservices.guestprofileservice.repository.GuestRepository;
import com.crs.microservices.guestprofileservice.repository.GuestRepositoryImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;

@RunWith(SpringRunner.class)
@SpringBootTest
public class GuestImplRepositoryTest {

    @MockBean
    private GuestRepository repository;

    @Autowired
    private GuestRepositoryImpl guestRepositoryImpl;

    private GuestEntity guestEntity =  new GuestEntity(1L, "Michael Kong","michaelkong@hotmail.com", "007878");

    @Test
    public void save() {
        given(repository.save(any())).willReturn(guestEntity);
        GuestEntity save = guestRepositoryImpl.save(guestEntity);

        assertEquals(save.getGuestId(), guestEntity.getGuestId());

    }

    @Test
    public  void findById() {
        given(repository.findById(anyLong())).willReturn(java.util.Optional.ofNullable(guestEntity));
        GuestEntity byId = guestRepositoryImpl.findById(1L);
        assertEquals(byId.getGuestId(), guestEntity.getGuestId());
    }
}