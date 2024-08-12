package com.example.HotelManagerment.Exceptions;

public class HotelNotFoundException extends RuntimeException {
    public HotelNotFoundException(String hotelNotFound) {
        super(hotelNotFound);
    }
}
