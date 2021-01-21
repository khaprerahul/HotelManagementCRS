package com.crs.microservices.hotelinformationservice.model;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import java.util.Date;

@JsonDeserialize(as = ReservationImpl.class)
public interface Reservation {
    java.lang.Long getReservationId();
    void setState(String state);
    String getState();
    public Room getRoom() ;
    public void setRoom(Room room);
    public Long getGuestId();
    public void setGuestId(Long guest);
    public Date getFromDate();
    public void setFromDate(Date fromDate);
    public Date getToDate();
    public void setToDate(Date toDate);
    public void setReservationId(java.lang.Long reservationId);

    public String getRoomType();

    public void setRoomType(String roomType);

}
