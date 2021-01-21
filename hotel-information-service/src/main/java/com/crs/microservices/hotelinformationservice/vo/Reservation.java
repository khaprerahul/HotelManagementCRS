package com.crs.microservices.hotelinformationservice.vo;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import java.util.Date;

@JsonDeserialize(as = ReservationVO.class)
public interface Reservation {
    java.lang.Long getReservationId();
    void setState(ReservationStatus state);
    ReservationStatus getState();
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
