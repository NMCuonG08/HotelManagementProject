package com.example.HotelManagerment.Exceptions;

public class PhotoRetrievalException extends RuntimeException {
    public PhotoRetrievalException(String errorPhotoHere) {
        super(errorPhotoHere);
    }
}
