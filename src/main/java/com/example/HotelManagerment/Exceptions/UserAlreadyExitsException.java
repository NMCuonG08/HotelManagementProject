package com.example.HotelManagerment.Exceptions;

public class UserAlreadyExitsException extends RuntimeException {
    public UserAlreadyExitsException(String s) {
        super(s);
    }
}
