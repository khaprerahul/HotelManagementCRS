package com.crs.microservices.hotelinformationservice.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Hotel implements IHotel {

	private Long hotelId;
	private String name;
	private String phoneNumber;
	private int starRatting;
	private IAddress address;
	private List<IRoom> rooms = new ArrayList<>();
	private List<IReservation> reservations = new ArrayList<>();
	private Map<Date, List<Long>> reservationsByDate = new HashMap<>();

	public Map<Date, List<Long>> getReservationsByDate() {
		return reservationsByDate;
	}

	public void setReservationsByDate(Map<Date, List<Long>> reservationsByDate) {
		this.reservationsByDate = reservationsByDate;
	}

	public List<IReservation> getReservations() {
		return reservations;
	}

	public Long getHotelId() {
		return hotelId;
	}

	public void setHotelId(Long hotelId) {
		this.hotelId = hotelId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public int getStarRatting() {
		return starRatting;
	}

	public void setStarRatting(int starRatting) {
		this.starRatting = starRatting;
	}

	public IAddress getAddress() {
		return address;
	}

	public void setAddress(IAddress address) {
		this.address = address;
	}

	public List<IRoom> getRooms() {
		return rooms;
	}

	public void setRooms(List<IRoom> rooms) {
		this.rooms = rooms;
	}

	public void setReservations(List<IReservation> reservations) {
		this.reservations = reservations;
	}
}
