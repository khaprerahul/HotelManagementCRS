package com.crs.microservices.hotelinformationservice.vo;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

@JsonDeserialize(as = RoomVO.class)
public interface Room {
    public int getRoomNo();

    public void setRoomNo(int roomNo);

    public double getRentPerNight();

    public void setRentPerNight(double rentPerNight);

    public RoomType getRoomType();

    public void setRoomType(RoomType roomType);

}
