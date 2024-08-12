package com.example.HotelManagerment.HotelInformation.HotelConvenient;

import java.util.List;

public interface IHotelConvenientService {
    HotelConvenient addNewConvenient(Long id, List<String> convenient);


    HotelConvenientResponse convertToHotelConvenientResponse(HotelConvenient hotelConvenient);

    HotelConvenient getAllConvenient(Long id);

    HotelConvenient updateConvenient(Long id, String type, List<String> convenient);
}
