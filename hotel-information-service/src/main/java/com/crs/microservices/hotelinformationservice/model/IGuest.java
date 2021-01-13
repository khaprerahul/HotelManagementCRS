package com.crs.microservices.hotelinformationservice.model;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

@JsonDeserialize(as = Guest.class)
public interface IGuest {
	public void setEmail(String email);

	public String getEmail();

	public void setGuestId(java.lang.Long guestId);

	public java.lang.Long getGuestId();

	public String getName();

	public void setName(String name);

	public String getContactNumber();

	public void setContactNumber(String contactNumber);
}
