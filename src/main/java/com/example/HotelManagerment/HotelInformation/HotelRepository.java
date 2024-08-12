package com.example.HotelManagerment.HotelInformation;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.sql.Blob;
import java.util.List;
import java.util.Optional;

public interface HotelRepository extends JpaRepository<HotelInformation, Long> {
    @Query("SELECT h.Image FROM HotelImage h WHERE h.hotelInformation.id = :hotelID")
    List<Blob> findHotelImageByID(@Param("hotelID") Long hotelID);

    @Query("Select u.hotel.id from User u where u.email = :email ")
    Optional<Long> findHotelIdByEmail(@Param("email") String email);
}
