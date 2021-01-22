package com.crs.microservices.hotelreservationservice.vo;

import java.util.ArrayList;
import java.util.List;

public class GuestVO implements Guest {

    private Long guestId;
    private String name;
    private String email;
    private String contactNumber;
    private int ratting;
    private List<Reservation> reservations =  new ArrayList<>();

    public Long getGuestId() {
        return guestId;
    }

    public void setGuestId(Long guestId) {
        this.guestId = guestId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getContactNumber() {
        return contactNumber;
    }

    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
    }

    public int getRatting() {
        return ratting;
    }

    public void setRatting(int ratting) {
        this.ratting = ratting;
    }

    public List<Reservation> getReservations() {
        return reservations;
    }

    public void setReservations(List<Reservation> reservations) {
        this.reservations = reservations;
    }

    public GuestVO() {
    }

    public GuestVO(Long guestId, String name, String email, String contactNumber, int ratting, List<Reservation> reservations) {
        this.guestId = guestId;
        this.name = name;
        this.email = email;
        this.contactNumber = contactNumber;
        this.ratting = ratting;
        this.reservations = reservations;
    }
}
