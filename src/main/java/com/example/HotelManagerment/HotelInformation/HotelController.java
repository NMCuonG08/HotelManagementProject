package com.example.HotelManagerment.HotelInformation;

import com.example.HotelManagerment.Exceptions.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.apache.catalina.connector.Response;
import org.springframework.data.relational.core.sql.In;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.sql.rowset.serial.SerialBlob;
import java.io.IOException;
import java.sql.Blob;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@CrossOrigin("http://localhost:5173")
@RestController
@RequiredArgsConstructor
@RequestMapping("/hotels")
public class HotelController {
    private final IHotelService hotelService;

    @PostMapping("/add/newhotel")
    public ResponseEntity<HotelResponse> addHotel(
            @RequestParam("HotelName") String HotelName,
            @RequestParam("City") String City,
            @RequestParam("Street") String Street,
            @RequestParam("FeedBack") Double FeedBack,
            @RequestParam("Price") Double Price,
            @RequestParam("Email") String Email,
            @RequestParam("Zipcode") String Zipcode,
            @RequestParam("FloorsNumber") Integer FloorsNumber,
            @RequestParam("Capacity") Integer Capacity,
            @RequestParam("PhoneNumber") String PhoneNumber,
            @RequestParam("Country") String Country,
            @RequestParam("Description") String Description,
            @RequestParam("Star") Double Star,
            @RequestParam("Type") String Type
            ){
        HotelInformation hotel = new HotelInformation(HotelName,City,Street,FeedBack,Price,Email,Zipcode,FloorsNumber,Capacity,PhoneNumber,Country,Description,Star,Type);
        HotelInformation theHotel = hotelService.addNewHotel(hotel);
        HotelResponse hotelResponse = hotelService.convertToResponse(theHotel);
        return ResponseEntity.ok(hotelResponse);
    }
    @GetMapping("/all-hotel")
    public ResponseEntity<List<HotelResponse>> getAllHotels(){
        List<HotelInformation> hotels = hotelService.getALlHotel();
        List<HotelResponse> hotelResponses = new ArrayList<>();
        for (HotelInformation hotel : hotels) {
                HotelResponse hotelResponse = hotelService.convertToResponse(hotel);
                hotelResponse.setID(hotel.getID());
                hotelResponses.add(hotelResponse);
        }
        return ResponseEntity.ok(hotelResponses);
    }
    @GetMapping("/hotel/{id}")
    public ResponseEntity<Optional<HotelResponse>> getHotelByID(@PathVariable Long id){
        Optional<HotelInformation> theHotel = hotelService.getHotelByID(id);
        return theHotel.map(hotel-> {
            HotelResponse hotelResponse = hotelService.getHotelInformation(hotel);
            return ResponseEntity.ok(Optional.of(hotelResponse));
        }).orElseThrow(()-> new ResourceNotFoundException("Hotel not found"));
    }

    @DeleteMapping("/delete/{hotelID}")
    public ResponseEntity<HotelResponse> DeleteHotel(@PathVariable Long hotelID){
        hotelService.deleteHotelByID(hotelID);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
    @PutMapping("/update/{HotelID}")
    public ResponseEntity<HotelResponse> UpdateHotel(@PathVariable Long HotelID,
                                                     @RequestParam("HotelName") String HotelName,
                                                     @RequestParam("City") String City,
                                                     @RequestParam("Street") String Street,
                                                     @RequestParam("FeedBack") Double FeedBack,
                                                     @RequestParam("Price") Double Price,
                                                     @RequestParam("Email") String Email,
                                                     @RequestParam("Zipcode") String Zipcode,
                                                     @RequestParam("FloorsNumber") Integer FloorsNumber,
                                                     @RequestParam("Capacity") Integer Capacity,
                                                     @RequestParam("PhoneNumber") String PhoneNumber,
                                                     @RequestParam("Country") String Country,
                                                     @RequestParam("Description") String Description,
                                                     @RequestParam("Star") Double Star,
                                                     @RequestParam("Type") String Type){
            HotelInformation hotel = new HotelInformation(HotelName,City,Street,FeedBack,Price,Email,Zipcode,FloorsNumber,Capacity,PhoneNumber,Country,Description,Star,Type);
            HotelInformation updateHotel = hotelService.updateHotel(HotelID, hotel);
            HotelResponse hotelResponse = hotelService.convertToResponse(updateHotel);
            return ResponseEntity.ok(hotelResponse);
    }
    @PostMapping("/hotel/{hotelID}/add-image")
    public ResponseEntity<Void> addHotelImages(@PathVariable Long hotelID,
                                               @RequestParam("photos") List<MultipartFile> photos) throws IOException, SQLException {
        Optional<HotelInformation> hotelInformationOptional = hotelService.getHotelByID(hotelID);
        if (hotelInformationOptional.isPresent()) {
            HotelInformation hotel = hotelInformationOptional.get();
            for (MultipartFile photo : photos) {
                byte[] photoByte = photo != null && !photo.isEmpty() ? photo.getBytes() : null;
                hotel = hotelService.AddHotelImage(hotelID, photoByte);
            }
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/hotel/{hotelID}/hotel-images")
    public List<String> getAllHotelImageByID(@PathVariable Long hotelID){
        return  hotelService.getAllHotelImageByID(hotelID);
    }
    @GetMapping("/findHotelId")
    public ResponseEntity<Long> getHotelIDByEmail(@RequestParam String email){
        Long hotelId = hotelService.getHotelIDByEmail(email);
        return ResponseEntity.ok(hotelId);
    }

}
