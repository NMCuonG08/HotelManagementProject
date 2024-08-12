package com.example.HotelManagerment.Search;

import com.example.HotelManagerment.Booking.BookingInformation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SearchBookingRepository extends JpaRepository<BookingInformation, Long> {
}
