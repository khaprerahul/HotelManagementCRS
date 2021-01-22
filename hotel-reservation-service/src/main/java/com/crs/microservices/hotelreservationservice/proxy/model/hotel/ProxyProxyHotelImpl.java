package com.crs.microservices.hotelreservationservice.proxy.model.hotel;

public class ProxyProxyHotelImpl implements ProxyHotel {

    private Long hotelId;
    private String name;
    private String phoneNumber;
    private int rating;
    private ProxyAddress proxyAddress;

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

    public ProxyAddress getAddress() {
        return proxyAddress;
    }

    public void setAddress(ProxyAddress proxyAddress) {
        this.proxyAddress = proxyAddress;
    }

    public ProxyProxyHotelImpl() {
    }

    public ProxyProxyHotelImpl(Long hotelId, String name, String phoneNumber, int rating, ProxyAddress proxyAddress) {
        this.hotelId = hotelId;
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.rating = rating;
        this.proxyAddress = proxyAddress;
    }
}
