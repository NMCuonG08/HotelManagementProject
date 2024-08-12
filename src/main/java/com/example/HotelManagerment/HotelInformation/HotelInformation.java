package com.example.HotelManagerment.HotelInformation;


import com.example.HotelManagerment.Booking.BookingInformation;
import com.example.HotelManagerment.Comment.CommentEntity;
import com.example.HotelManagerment.HotelInformation.HotelConvenient.HotelConvenient;
import com.example.HotelManagerment.RoomInformation.RoomInformation;
import com.example.HotelManagerment.User.User;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.sql.Blob;
import java.util.ArrayList;
import java.util.List;

@Entity
@Setter
@Getter
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class HotelInformation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long ID;

    @Column(name = "hotel_name")
    private String HotelName;

    @Column(name = "city")
    private String City;

    @Column(name = "street")
    private String Street;

    @Column(name = "feedback")
    private Double FeedBack;

    @Column(name = "price")
    private Double Price;

    @Column(name = "email")
    private String Email;

    @Column(name = "zipcode")
    private String Zipcode;

    @Column(name = "floors_number")
    private Integer FloorsNumber;

    @Column(name = "capacity")
    private Integer Capacity;

    @Column(name = "phone_number")
    private String PhoneNumber;

    @Column(name = "country")
    private String Country;

    @Column(name = "description")
    private String Description;

    @Column(name = "lat_point")
    private Double LatPoint;

    @Column(name = "lng_point")
    private Double LngPoint;
    @Column(name = "star")
    private Double Star;
    @Column(name = "type")
    private String Type;

    @OneToOne(mappedBy = "hotel", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonIgnore
    private User Admin;

    @OneToMany(mappedBy = "hotelInformation", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonIgnore
    private List<HotelImage> images;
    @OneToMany(mappedBy = "hotel", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonIgnore
    private List<RoomInformation> rooms;

    @OneToMany(mappedBy =  "hotelInformation", cascade =  CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonIgnore
    private List<CommentEntity> comments;

    @OneToMany(mappedBy = "hotelInformation", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonIgnore
    private List<BookingInformation> bookings;


    public HotelInformation() {
    }


    public HotelInformation(String hotelName, String city, String street, Double feedBack, Double price, String email, String zipcode, Integer floorsNumber, Integer capacity, String phoneNumber, String country, String description,Double star, String type) {
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

    public void addImage(Blob imageBlob){
        HotelImage image = new HotelImage();
        image.setImage(imageBlob);
        image.setHotelInformation(this);
        if (images == null) {
            images = new ArrayList<>();
        }
        images.add(image);
    }

}
