package com.example.HotelManagerment.Booking;


import com.example.HotelManagerment.HotelInformation.HotelInformation;
import com.example.HotelManagerment.Payment.PaymentSaveData.PaymentEntity;
import com.example.HotelManagerment.User.User;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class BookingInformation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "customer_name")
    private String customerName;
    @Column(name = "email")
    private String email;
    @Column(name = "phone_number")
    private String phoneNumber;
    @Column(name = "check_in")
    private LocalDate checkIn;
    @Column(name = "check_out")
    private LocalDate checkOut;
    @Column(name = "booking_date")
    private LocalDate bookingDate;
    @Column(name = "total_price")
    private Double totalPrice;
    @Column(name = "is_checkout")
    private Boolean isCheckOut = false;
    @Column(name = "booking_status")
    private String bookingStatus;
    private String required;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "userID")
    @JsonIgnore
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "hotelId")
    @JsonIgnore
    private HotelInformation hotelInformation;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "paymentID")
    @JsonIgnore
    private PaymentEntity payment;

    public BookingInformation(String customerName, String email, String phoneNumber, LocalDate checkIn, LocalDate checkOut, LocalDate bookingDate, Double totalPrice, Boolean isCheckOut, String bookingStatus,String required ,User user) {
        this.customerName = customerName;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.checkIn = checkIn;
        this.checkOut = checkOut;
        this.bookingDate = bookingDate;
        this.totalPrice = totalPrice;
        this.isCheckOut = isCheckOut;
        this.bookingStatus = bookingStatus;
        this.user = user;
        this.required = required;
    }

    public BookingInformation(String customerName, String email, String phoneNumber, LocalDate checkIn, LocalDate checkOut, LocalDate bookingDate, Double totalPrice, Boolean isCheckOut, String bookingStatus, String required, User user, HotelInformation hotelInformation, PaymentEntity payment) {
        this.customerName = customerName;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.checkIn = checkIn;
        this.checkOut = checkOut;
        this.bookingDate = bookingDate;
        this.totalPrice = totalPrice;
        this.isCheckOut = isCheckOut;
        this.bookingStatus = bookingStatus;
        this.required = required;
        this.user = user;
        this.hotelInformation = hotelInformation;
        this.payment = payment;
    }



}
