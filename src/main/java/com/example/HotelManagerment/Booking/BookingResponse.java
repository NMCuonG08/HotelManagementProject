package com.example.HotelManagerment.Booking;


import com.example.HotelManagerment.HotelInformation.HotelInformation;
import com.example.HotelManagerment.Payment.PaymentSaveData.PaymentEntity;
import com.example.HotelManagerment.User.User;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Data
@Setter
@Getter
@NoArgsConstructor

public class BookingResponse {
    private Long id;

    private String customerName;

    private String email;

    private String phoneNumber;

    private LocalDate checkIn;

    private LocalDate checkOut;

    private LocalDate bookingDate;

    private Double totalPrice;

    private Boolean IsCheckOut;

    private String bookingStatus;

    private  String required;

    private User user;

    private HotelInformation hotelInformation;

    private PaymentEntity payment;

    public BookingResponse(String customerName, String email, String phoneNumber, LocalDate checkIn, LocalDate checkOut, LocalDate bookingDate, Double totalPrice, Boolean isCheckOut, String bookingStatus, String required, User user, HotelInformation hotelInformation, PaymentEntity payment) {
        this.customerName = customerName;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.checkIn = checkIn;
        this.checkOut = checkOut;
        this.bookingDate = bookingDate;
        this.totalPrice = totalPrice;
        IsCheckOut = isCheckOut;
        this.bookingStatus = bookingStatus;
        this.required = required;
        this.user = user;
        this.hotelInformation = hotelInformation;
        this.payment = payment;
    }
}
