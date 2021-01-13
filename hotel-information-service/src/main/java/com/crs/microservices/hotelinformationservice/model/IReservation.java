package com.crs.microservices.hotelinformationservice.model;

import java.util.Date;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

@JsonDeserialize(as = Reservation.class)

public interface IReservation {
	java.lang.Long getReservationId();

	void setState(String state);

	String getState();

	public IRoom getRoom();

	public void setRoom(IRoom room);

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
