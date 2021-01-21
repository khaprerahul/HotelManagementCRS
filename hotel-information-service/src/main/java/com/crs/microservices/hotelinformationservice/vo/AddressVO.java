package com.crs.microservices.hotelinformationservice.vo;

public class AddressVO implements Address {

    private Long addressId;
    private String street;
    private String area;
    private String city;
    private String pin;

    public String getPin() {
        return pin;
    }

    public void setPin(String pin) {
        this.pin = pin;
    }

    public AddressVO(){}

    public String getStreet() {
        return street;
    }

    public Long getAddressId() {
        return addressId;
    }

    public void setAddressId(Long addressId) {
        this.addressId = addressId;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public AddressVO(long addressId, String street, String area, String city, String pin) {
        this.addressId = addressId;
        this.street = street;
        this.area = area;
        this.city = city;
        this.pin = pin;
    }

    @Override
    public String toString() {
        return "Address{" +
                "street='" + street + '\'' +
                ", area='" + area + '\'' +
                ", city='" + city + '\'' +
                '}';
    }
}
