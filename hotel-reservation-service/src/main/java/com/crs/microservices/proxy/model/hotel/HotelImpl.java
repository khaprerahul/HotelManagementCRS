package com.crs.microservices.proxy.model.hotel;

import java.util.Objects;

public class HotelImpl implements Hotel {
    private Long hotelId;
    private String name;
    private String phoneNumber;
    private int starRatting;
    private Address address;

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

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public HotelImpl() {
    }

    public HotelImpl(Long hotelId, String name, String phoneNumber, int starRatting, Address address) {
        this.hotelId = hotelId;
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.starRatting = starRatting;
        this.address = address;
    }
}