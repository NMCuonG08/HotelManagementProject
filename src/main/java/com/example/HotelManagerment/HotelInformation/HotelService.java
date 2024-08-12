package com.example.HotelManagerment.HotelInformation;

import com.example.HotelManagerment.Exceptions.HotelNotFoundException;
import com.example.HotelManagerment.Exceptions.InternalServerException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import com.example.HotelManagerment.Exceptions.ResourceNotFoundException;
import org.springframework.web.multipart.MultipartFile;

import javax.sql.rowset.serial.SerialBlob;
import javax.swing.text.html.Option;
import java.sql.Blob;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class HotelService implements IHotelService {

    private final HotelRepository hotelRepository;

    public HotelResponse convertToResponse(HotelInformation hotel) {
        HotelResponse response = new HotelResponse();
        response.setID(hotel.getID());
        response.setHotelName(hotel.getHotelName());
        response.setCity(hotel.getCity());
        response.setStreet(hotel.getStreet());
        response.setFeedBack(hotel.getFeedBack());
        response.setPrice(hotel.getPrice());
        response.setEmail(hotel.getEmail());
        response.setZipcode(hotel.getZipcode());
        response.setFloorsNumber(hotel.getFloorsNumber());
        response.setCapacity(hotel.getCapacity());
        response.setPhoneNumber(hotel.getPhoneNumber());
        response.setCountry(hotel.getCountry());
        response.setDescription(hotel.getDescription());
        response.setStar(hotel.getStar());
        response.setType(hotel.getType());
        return response;
    }

    public HotelResponse getHotelInformation(HotelInformation hotel) {
        HotelResponse response = new HotelResponse();
        response.setID(hotel.getID());
        response.setHotelName(hotel.getHotelName());
        response.setCity(hotel.getCity());
        response.setStreet(hotel.getStreet());
        response.setFeedBack(hotel.getFeedBack());
        response.setPrice(hotel.getPrice());
        response.setEmail(hotel.getEmail());
        response.setZipcode(hotel.getZipcode());
        response.setFloorsNumber(hotel.getFloorsNumber());
        response.setCapacity(hotel.getCapacity());
        response.setPhoneNumber(hotel.getPhoneNumber());
        response.setCountry(hotel.getCountry());
        response.setDescription(hotel.getDescription());
        response.setStar(hotel.getStar());
        response.setType(hotel.getType());
        return response;
    }

    @Override
    public Optional<HotelInformation> getHotelByID(Long id) {
        return hotelRepository.findById(id);
    }

    @Override
    public HotelInformation AddHotelImage(Long hotelID, byte[] photoByte) {
        HotelInformation hotel = hotelRepository.findById(hotelID).orElseThrow(() -> new ResourceNotFoundException("Hotel not found"));
        if (photoByte != null && photoByte.length > 0){
            try{
                hotel.addImage(new SerialBlob(photoByte));
            }
            catch (Exception ex) {
                    throw new InternalServerException("Error Adding Image ");
            }
        }
        return hotelRepository.save(hotel);
    }

    @Override
    public List<String> getAllHotelImageByID(Long hotelID) {
        List<Blob> hotelImages = hotelRepository.findHotelImageByID(hotelID);
        List<String> results = new ArrayList<>();
        for (Blob hotelImage : hotelImages) {
            try {
                byte[] bytes = hotelImage.getBytes(1, (int) hotelImage.length());
                String base64String = Base64.getEncoder().encodeToString(bytes);
                results.add(base64String);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return results;
    }

    @Override
    public Long getHotelIDByEmail(String email) {
        return hotelRepository.findHotelIdByEmail(email).orElseThrow(() -> new HotelNotFoundException("Hotel not found"));
    }


    @Override
    public HotelInformation addNewHotel(HotelInformation hotel) {
        return hotelRepository.save(hotel);
    }

    @Override
    public List<HotelInformation> getALlHotel() {
        return hotelRepository.findAll();
    }

    @Override
    public void deleteHotelByID(Long hotelID) {
        Optional<HotelInformation> hotel = hotelRepository.findById(hotelID);
        if (hotel.isPresent()){
            hotelRepository.deleteById(hotelID);
        }

    }

    @Override
    public HotelInformation updateHotel(Long hotelID, HotelInformation hotel) {
        HotelInformation theHotel = hotelRepository.findById(hotelID)
                .orElseThrow(() -> new ResourceNotFoundException("Hotel not found"));
        if(hotel.getHotelName() != null) theHotel.setHotelName(hotel.getHotelName());
        if (hotel.getCity() != null) theHotel.setCity(hotel.getCity());
        if (hotel.getStreet() != null) theHotel.setStreet(hotel.getStreet());
        if (hotel.getFeedBack() != null) theHotel.setFeedBack(hotel.getFeedBack());
        if (hotel.getPrice() != null) theHotel.setPrice(hotel.getPrice());
        if (hotel.getEmail() != null) theHotel.setEmail(hotel.getEmail());
        if (hotel.getZipcode() != null) theHotel.setZipcode(hotel.getZipcode());
        if (hotel.getFloorsNumber() != null) theHotel.setFloorsNumber(hotel.getFloorsNumber());
        if (hotel.getCapacity() != null) theHotel.setCapacity(hotel.getCapacity());
        if (hotel.getPhoneNumber() != null) theHotel.setPhoneNumber(hotel.getPhoneNumber());
        if (hotel.getCountry() != null) theHotel.setCountry(hotel.getCountry());
        if (hotel.getDescription() != null) theHotel.setDescription(hotel.getDescription());
        if (hotel.getStar() != null) theHotel.setStar(hotel.getStar());
        if (hotel.getType() != null) theHotel.setType(hotel.getType());
        return hotelRepository.save(theHotel);
    }
}
