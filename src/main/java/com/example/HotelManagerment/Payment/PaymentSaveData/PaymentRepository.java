package com.example.HotelManagerment.Payment.PaymentSaveData;

import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentRepository extends JpaRepository<PaymentEntity, Long> {
}
