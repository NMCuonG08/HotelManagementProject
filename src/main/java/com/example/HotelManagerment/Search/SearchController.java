package com.example.HotelManagerment.Search;


import com.example.HotelManagerment.HotelInformation.HotelInformation;
import com.example.HotelManagerment.HotelInformation.HotelResponse;
import com.example.HotelManagerment.HotelInformation.HotelService;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin("http://localhost:5173")
@RequiredArgsConstructor
@RequestMapping("/search")
public class SearchController {

    private final ISearchService searchService;
    private final HotelService hotelService;

    @GetMapping("/location")
    public List<String>  SearchLocation(@RequestParam("location") String location){
        return  searchService.findLocation(location);
    }

    @GetMapping("/filter")
    public ResponseEntity<List<HotelResponse>> filterHotel(
            @RequestParam("location") String location,
            @RequestParam("StartDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate StartDate,
            @RequestParam("EndDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate EndDate,
            @RequestParam("NumberOfGuest") Integer NumberOfGuest,
            @RequestParam("Type") String Type
            ){
        List< HotelInformation > hotels = searchService.filterHotel(location, StartDate, EndDate, NumberOfGuest, Type);
        List<HotelResponse> theHotels = new ArrayList<>();
        for(HotelInformation h : hotels ){
            theHotels.add(hotelService.convertToResponse(h));
        }
        return ResponseEntity.ok(theHotels);
    }

}
