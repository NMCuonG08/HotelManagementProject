package com.example.HotelManagerment.Payment.PaymentSaveData;

import com.example.HotelManagerment.Booking.BookingInformation;
import lombok.*;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter

public class PaymentResponse {
    private Long id;

    private Double amount;

    private String status;

    private String typePayment;

    private String transactionID;

    private LocalDate timePayment;

    private BookingInformation bookingInformation;


}
