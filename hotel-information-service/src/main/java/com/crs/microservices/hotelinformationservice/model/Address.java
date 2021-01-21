package com.crs.microservices.hotelinformationservice.model;


import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

@JsonDeserialize(as = AddressImpl.class)
public interface Address {
    public String getStreet();

    public String getPin();

    public void setPin(String pin);

    public Long getAddressId();

    public void setAddressId(Long addressId);

    public void setStreet(String street);

    public String getArea();

    public void setArea(String area);

    public String getCity();

    public void setCity(String city);

}
