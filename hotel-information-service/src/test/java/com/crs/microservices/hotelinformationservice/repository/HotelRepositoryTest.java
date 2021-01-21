package com.crs.microservices.hotelinformationservice.repository;

import com.crs.microservices.hotelinformationservice.entity.AddressEntity;
import com.crs.microservices.hotelinformationservice.entity.HotelEntity;
import com.crs.microservices.hotelinformationservice.entity.ReservationEntity;
import com.crs.microservices.hotelinformationservice.entity.RoomEntity;
import com.crs.microservices.hotelinformationservice.vo.RoomType;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import javax.persistence.EntityNotFoundException;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;

@RunWith(SpringRunner.class)
@SpringBootTest
public class HotelRepositoryTest {

    @MockBean
    private HotelRepository repository;

    @Autowired
    private HotelRepositoryImpl hotelRepositoryImpl;

    @MockBean
    private ReservationRepository reservationRepository;

    @Autowired
    private ReservationRepositoryImpl reservationRepositoryImpl;


    private AddressEntity addressEntity = new AddressEntity(1L, "Nagar Pune Road", "KedGaon", "Ahmednagar", "414001");
    private HotelEntity hotelEntity = new HotelEntity(1L, "Hotel Yash Grand", "0241-2411429", 2, addressEntity);
    private RoomEntity roomEntity = new RoomEntity(501, 4500, RoomType.KING);
    private ReservationEntity reservationEntity = new ReservationEntity(new RoomEntity(), 1L, new Date(), new Date(), 1L, "REQUESTED", "DELUXE");


    @Test
        public void save() {
            given(repository.save(any())).willReturn(hotelEntity);
            HotelEntity save = hotelRepositoryImpl.save(hotelEntity);

        assertEquals(save.getHotelId(), hotelEntity.getHotelId());

    }


    @Test
    public  void findById() {
        given(repository.findById(anyLong())).willReturn(java.util.Optional.ofNullable(hotelEntity));
        HotelEntity byId = hotelRepositoryImpl.findById(1L);
        assertEquals(byId.getHotelId(), hotelEntity.getHotelId());
    }

    @Test(expected = EntityNotFoundException.class)
    public void findById_EntityNotFound() {
        given(repository.findById(anyLong())).willThrow(new EntityNotFoundException("Entity not found"));
        HotelEntity byId = hotelRepositoryImpl.findById(2L);
        assertEquals(byId.getHotelId(), hotelEntity.getHotelId());
    }
}
