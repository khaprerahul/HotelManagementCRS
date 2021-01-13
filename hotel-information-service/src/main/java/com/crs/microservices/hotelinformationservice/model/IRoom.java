package com.crs.microservices.hotelinformationservice.model;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

@JsonDeserialize(as = Room.class)
public interface IRoom {
	public int getRoomNo();

	public void setRoomNo(int roomNo);

	public double getRentPerNight();

	public void setRentPerNight(double rentPerNight);

	public RoomType getRoomType();

	public void setRoomType(RoomType roomType);
}
