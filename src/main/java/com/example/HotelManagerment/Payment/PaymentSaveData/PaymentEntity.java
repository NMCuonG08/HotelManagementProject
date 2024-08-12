package com.example.HotelManagerment.Payment.PaymentSaveData;

import com.example.HotelManagerment.Booking.BookingInformation;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Setter
@Getter
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class PaymentEntity {

    @Id
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    private Long id;

    private Double amount;

    private String status;

    private String typePayment;

    private String transactionID;

    private LocalDate timePayment;

    @OneToOne(mappedBy = "payment"  , cascade = CascadeType.ALL, fetch =  FetchType.LAZY)
    @JsonIgnore
    private BookingInformation bookingInformation;

}
