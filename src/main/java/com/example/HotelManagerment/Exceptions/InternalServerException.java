package com.example.HotelManagerment.Exceptions;

public class InternalServerException extends RuntimeException {
    public InternalServerException(String errorAddingImage) {
        super(errorAddingImage);
    }
}
