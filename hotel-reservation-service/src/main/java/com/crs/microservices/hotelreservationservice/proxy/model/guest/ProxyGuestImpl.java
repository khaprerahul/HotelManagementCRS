package com.crs.microservices.hotelreservationservice.proxy.model.guest;

import com.crs.microservices.hotelreservationservice.proxy.model.card.ICard;

import java.util.ArrayList;
import java.util.List;

public class ProxyGuestImpl implements ProxyGuest {

    private Long guestId;
    private String name;
    private String email;
    private String contactNumber;
    private int ratting;
    private List<ICard> card;
    private List<Long> reservations =  new ArrayList<>();
    public List<ICard> getCard() {
        return card;
    }

    public void setReservations(List<Long> reservations) {
        this.reservations = reservations;
    }

    public List<Long> getReservations() {
        return reservations;
    }

    public void setCard(List<ICard> card) {
        this.card = card;
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

    public int getRatting() {
        return ratting;
    }

    public void setRatting(int ratting) {
        this.ratting = ratting;
    }

    public ProxyGuestImpl() {
    }

    public ProxyGuestImpl(Long guestId, String name, String email, String contactNumber) {
        this.guestId = guestId;
        this.name = name;
        this.email = email;
        this.contactNumber = contactNumber;
    }
}
