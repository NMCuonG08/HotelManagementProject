package com.example.HotelManagerment.Search;

import com.example.HotelManagerment.HotelInformation.HotelInformation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface SearchHotelRepository  extends JpaRepository<HotelInformation, Long> {
    @Query("SELECT distinct City as loc FROM HotelInformation h WHERE h.City LIKE %:location% ")
    List<String> findByLocation(@Param("location") String character);
    @Query("SELECT DISTINCT h FROM HotelInformation h " +
            "JOIN h.rooms r " +
            "WHERE r.Status = 'Empty' " +
            "AND (:location IS NULL OR h.City LIKE %:location%) " +
            "AND (:startDate IS NULL OR :endDate IS NULL OR " +
            "(r.CheckIn NOT BETWEEN :startDate AND :endDate AND r.CheckOut NOT BETWEEN :startDate AND :endDate)) " +
            "AND (:numberOfGuest IS NULL OR r.Client >= :numberOfGuest)" +
            "AND (:type IS NULL OR h.Type LIKE %:type%) "
    )
    List<HotelInformation> findHotelsByCriteria(@Param("location") String location,
                                                @Param("startDate") LocalDate startDate,
                                                @Param("endDate") LocalDate endDate,
                                                @Param("numberOfGuest") Integer numberOfGuest,
                                                @Param("type") String type);
}
