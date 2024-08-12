package com.example.HotelManagerment.HotelInformation;

import com.example.HotelManagerment.Booking.BookingInformation;
import com.example.HotelManagerment.RoomInformation.RoomInformation;
import com.example.HotelManagerment.User.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Data
@AllArgsConstructor
@Getter
@Setter
public class HotelResponse {

    private Long ID;
    private String HotelName;
    private String City;
    private String Street;
    private Double FeedBack;
    private Double Price;
    private String Email;
    private String Zipcode;
    private Integer FloorsNumber;
    private Integer Capacity;
    private String PhoneNumber;
    private String Country;
    private String Description;
    private Double LatPoint;
    private Double LngPoint;
    private Double Star;
    private String Type;
    private User Admin;
    private List<String> ImageUrls;
    private List<RoomInformation> Rooms;
    private List<BookingInformation> bookings;
    public HotelResponse() {
    }


    public HotelResponse(String hotelName, String city, String street, Double feedBack, Double price, String email, String zipcode, Integer floorsNumber, Integer capacity, String phoneNumber, String country, String description, Double star,String type) {
        HotelName = hotelName;
        City = city;
        Street = street;
        FeedBack = feedBack;
        Price = price;
        Email = email;
        Zipcode = zipcode;
        FloorsNumber = floorsNumber;
        Capacity = capacity;
        PhoneNumber = phoneNumber;
        Country = country;
        Description = description;
        Star = star;
        Type = type;
    }
}
