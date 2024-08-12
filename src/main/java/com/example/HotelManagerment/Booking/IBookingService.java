package com.example.HotelManagerment.Booking;

import java.util.List;
import java.util.Optional;

public interface IBookingService {
    void addBooking(BookingInformation bookingInformation);
    BookingResponse convertFromBookingInfor(BookingInformation bookingInformation);

    List<BookingInformation> getALlBookingByUserID(Long id);

    BookingInformation changeBookingInfo(Long bookingId, String userName, String email, String phoneNumber, String required);

    List<BookingInformation> getBookingsByHotelId(Long hotelId);
}
