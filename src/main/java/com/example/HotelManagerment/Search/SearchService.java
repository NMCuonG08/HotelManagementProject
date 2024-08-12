package com.example.HotelManagerment.Search;

import com.example.HotelManagerment.HotelInformation.HotelInformation;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class SearchService implements ISearchService {

    private final SearchBookingRepository bookingRepository;
    private final SearchHotelRepository hotelRepository;


    @Override
    public List<String> findLocation(String character) {
        return hotelRepository.findByLocation(character);
    }

    @Override
    public List<HotelInformation> filterHotel(String location, LocalDate startDate, LocalDate endDate, Integer numberOfGuest, String Type) {
        return hotelRepository.findHotelsByCriteria(location, startDate, endDate, numberOfGuest, Type);
    }
}
