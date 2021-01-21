package com.crs.microservices.hotelinformationservice.vo;

import java.util.*;


public class Hotel implements IHotel {

    private Long hotelId;
    private String name;
    private String phoneNumber;
    private int rating;
    private Address address;
    private List<Room> rooms = new ArrayList() ;
    private List<Reservation> reservations = new ArrayList();
    private Map<Date, List<Long>> reservationsByDate =  new HashMap();
    public Map<Date, List<Long>> getReservationsByDate() {
        return reservationsByDate;
    }

    public void setReservationsByDate(Map<Date, List<Long>> reservationsByDate) {
        this.reservationsByDate = reservationsByDate;
    }

    public List<Reservation> getReservations() {
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
    public int getRating() {
        return rating;
    }
    public void setRating(int rating) {
        this.rating = rating;
    }
    public Address getAddress() {
        return address;
    }
    public void setAddress(Address address) {
        this.address = address;
    }
    public List<Room> getRooms() {
        return rooms;
    }
    public void setRooms(List<Room> rooms) {
        this.rooms = rooms;
    }
    public void setReservations(List<Reservation> reservations) {
        this.reservations = reservations;
    }

    public Hotel() {

    }

    public Hotel(Long hotelId, String name, String phoneNumber, int rating, Address address) {
        this.hotelId = hotelId;
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.rating = rating;
        this.address = address;
    }

    @Override
    public String toString() {
        return "Hotel{" +
                "hotelId=" + hotelId +
                ", name='" + name + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", starRatting=" + rating +
                ", address=" + address +
                ", rooms=" + rooms +
                ", reservations=" + reservations +
                ", reservationsByDate=" + reservationsByDate +
                '}';
    }
}
