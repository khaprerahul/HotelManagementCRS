package com.crs.microservices.hotelinformationservice.model;

import java.util.Date;
import java.util.Objects;

public class Reservation implements IReservation {

	private IRoom room;
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

	public Reservation() {
	}

	public IRoom getRoom() {
		return room;
	}

	public void setRoom(IRoom room) {
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
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		Reservation that = (Reservation) o;
		return Objects.equals(reservationId, that.reservationId);
	}

	@Override
	public int hashCode() {
		return Objects.hash(reservationId);
	}

}
