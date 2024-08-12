package com.example.HotelManagerment.HotelInformation;

import org.springframework.web.multipart.MultipartFile;

import java.sql.Blob;
import java.util.List;
import java.util.Optional;

public interface IHotelService {
    public HotelResponse convertToResponse(HotelInformation hotel);
    HotelInformation addNewHotel(HotelInformation hotel);

    List<HotelInformation> getALlHotel();

    void deleteHotelByID(Long hotelID);

    HotelInformation updateHotel(Long hotelID, HotelInformation hotel);

    public HotelResponse getHotelInformation(HotelInformation hotel);

    Optional<HotelInformation> getHotelByID(Long id);


    HotelInformation AddHotelImage(Long hotelID, byte[] photoByte);

    List<String> getAllHotelImageByID(Long hotelID);

    Long getHotelIDByEmail(String email);
}
