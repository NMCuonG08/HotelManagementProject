package com.example.HotelManagerment.Search;

import com.example.HotelManagerment.HotelInformation.HotelInformation;

import java.time.LocalDate;
import java.util.List;

public interface ISearchService {
    List<String> findLocation(String character);

    List<HotelInformation> filterHotel(String location, LocalDate startDate, LocalDate endDate, Integer numberOfGuest, String Type);
}
