package com.example.HotelManagerment.Payment.PaymentSaveData;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor

public class PaymentService implements IPaymentService {

    private final PaymentRepository paymentRepository;


    @Override
    public PaymentEntity getPaymentById(Long id) {
        Optional<PaymentEntity> payment = paymentRepository.findById(id);
        if(payment.isPresent()){
            return payment.get();
        }
        else {
            return null;
        }
    }
}
