package com.crs.microservices.hotelinformationservice.vo;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import java.util.Date;
import java.util.List;
import java.util.Map;

@JsonDeserialize(as = Hotel.class)
public interface IHotel {
    Long getHotelId();
    List<Reservation> getReservations();
    public void setHotelId(Long hotelId);
    public String getName();
    public void setName(String name);
    public String getPhoneNumber();
    public void setPhoneNumber(String phoneNumber);
    public int getRating();
    public void setRating(int rating);
    public Address getAddress();
    public void setAddress(Address address);
    public List<Room> getRooms();
    public void setRooms(List<Room> rooms);
    public void setReservations(List<Reservation> reservation);
    public Map<Date, List<Long>> getReservationsByDate();
    public void setReservationsByDate(Map<Date, List<Long>> reservationsByDate);

}
