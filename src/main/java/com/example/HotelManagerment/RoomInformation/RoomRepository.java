package com.example.HotelManagerment.RoomInformation;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.sql.Blob;
import java.util.List;

public interface RoomRepository extends JpaRepository<RoomInformation, Long> {
    @Query("SELECT r FROM RoomInformation r WHERE r.hotel.id = :hotelID")
    List<RoomInformation> getRoomByHotelID(@Param("hotelID") Long hotelID);
    @Query("SELECT h.Image FROM RoomImages h WHERE h.room.id = :roomID")
    List<Blob> findHotelImageByID(@Param("roomID") Long roomID);
}

