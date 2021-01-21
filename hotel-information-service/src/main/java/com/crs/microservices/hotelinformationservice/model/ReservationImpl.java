package com.crs.microservices.hotelinformationservice.model;

import java.util.Date;
import java.util.Objects;


public class ReservationImpl implements Reservation {
    private Room room;
    private Long guestId;
    private Date fromDate;
    private Date toDate;
    private Long reservationId;
    private String state;
    private String roomType;

    public String getRoomType() {
        return roomType;
    }

    public void setRoomType(String roomType) {
        this.roomType = roomType;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public ReservationImpl(){}
    public Room getRoom() {
        return room;
    }

    public void setRoom(Room room) {
        this.room = room;
    }

    public Long getGuestId() {
        return guestId;
    }

    public void setGuestId(Long guestId) {
        this.guestId = guestId;
    }

    public Date getFromDate() {
        return fromDate;
    }

    public void setFromDate(Date fromDate) {
        this.fromDate = fromDate;
    }

    public Date getToDate() {
        return toDate;
    }

    public void setToDate(Date toDate) {
        this.toDate = toDate;
    }

    public Long getReservationId() {
        return reservationId;
    }

    public void setReservationId(Long reservationId) {
        this.reservationId = reservationId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ReservationImpl that = (ReservationImpl) o;
        return Objects.equals(reservationId, that.reservationId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(reservationId);
    }

    public ReservationImpl(Room room, Long guestId, Date fromDate, Date toDate, Long reservationId, String state, String roomType) {
        this.room = room;
        this.guestId = guestId;
        this.fromDate = fromDate;
        this.toDate = toDate;
        this.reservationId = reservationId;
        this.state = state;
        this.roomType = roomType;
    }
}
