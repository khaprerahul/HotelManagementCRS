package com.crs.microservices.hotelreservationservice.proxy.model.hotel;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

@JsonDeserialize(as = ProxyProxyAddressImpl.class)
public interface ProxyAddress {
    public Long getAddressId();

    public void setAddressId(Long addressId);

    public String getStreet();

    public void setStreet(String street);

    public String getArea();

    public void setArea(String area);

    public String getCity();

    public void setCity(String city);
}
