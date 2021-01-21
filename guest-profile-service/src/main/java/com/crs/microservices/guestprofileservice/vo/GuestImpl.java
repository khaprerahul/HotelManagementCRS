package com.crs.microservices.guestprofileservice.vo;

import java.util.ArrayList;
import java.util.List;


public class GuestImpl implements Guest {
    private Long guestId;
    private String name;
    private String email;
    private String contactNumber;
    private int rating;
    private List<Long> reservations =  new ArrayList<>();

    private List<Card> cards = new ArrayList<>();

    public List<Long> getReservations() {
        return reservations;
    }

    public void setReservations(List<Long> reservations) {
        this.reservations = reservations;
    }

    public List<Card> getCards() {
        return cards;
    }

    public void setCards(List<Card> cards) {
        this.cards = cards;
    }

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

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public GuestImpl(){}

    public GuestImpl(Long guestId, String name, String email, String contactNumber) {
        this.guestId = guestId;
        this.name = name;
        this.email = email;
        this.contactNumber = contactNumber;
    }

    @Override
    public String toString() {
        return "Guest{" +
                "guestId=" + guestId +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", contactNumber='" + contactNumber + '\'' +
                ", ratting=" + rating +
                ", reservations=" + reservations +
                ", cards=" + cards +
                '}';
    }
}
