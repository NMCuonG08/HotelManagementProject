package com.example.HotelManagerment.RoomInformation.RoomConvenient;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoomConvenientRepository extends JpaRepository<RoomConvenient, Long> {


    Optional<RoomConvenient> findByRoomID(Long id);
}
