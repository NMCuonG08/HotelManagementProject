package com.example.HotelManagerment.Exceptions;

public class RoomNotFoundException extends RuntimeException {
    public RoomNotFoundException(String roomNotFound) {
        super(roomNotFound);
    }
}
