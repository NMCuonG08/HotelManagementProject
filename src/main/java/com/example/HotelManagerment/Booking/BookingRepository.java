package com.example.HotelManagerment.Booking;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.security.core.parameters.P;

import java.util.List;
import java.util.Optional;

public interface BookingRepository extends JpaRepository<BookingInformation, Long> {
    @Query("Select b from BookingInformation b where b.user.id = :userId")
    List<BookingInformation> getALlBookingByUserID(@Param("userId") Long id);
    @Modifying
    @Query("UPDATE BookingInformation b SET b.customerName = :userName," +
            " b.email = :email," +
            " b.phoneNumber = :phoneNumber," +
            " b.required = :required where b.id = :id ")
    void updateBookingInfo(@Param("id") Long bookingId,
                             @Param("userName") String userName,
                            @Param("email") String email,
                            @Param("phoneNumber") String phoneNumber,
                            @Param("required") String required);
    @Query("Select b from BookingInformation b where b.hotelInformation.ID = :hotelId")
    List<BookingInformation> findByHotelId(@Param("hotelId") Long hotelId);
}
