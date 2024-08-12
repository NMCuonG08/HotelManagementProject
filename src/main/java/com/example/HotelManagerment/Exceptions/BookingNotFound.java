package com.example.HotelManagerment.Exceptions;

public class BookingNotFound extends RuntimeException {
    public BookingNotFound(String s) {
        super(s);
    }
}
