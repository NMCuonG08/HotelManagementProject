package com.example.HotelManagerment.User;


import com.example.HotelManagerment.Booking.BookingInformation;
import com.example.HotelManagerment.Comment.CommentEntity;
import com.example.HotelManagerment.HotelInformation.HotelInformation;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Locale;

@Entity
@Setter
@Getter
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "email")
    private String email;

    @Column(name = "password")
    private String password;

    @OneToOne
    @JoinColumn(name = "hotelID")
    private HotelInformation hotel;

    @Column(name = "isEnable")
    private boolean isEnabled ;

    @Column(name= "role")
    private String role;

    @OneToMany(mappedBy = "user" , cascade =  CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonIgnore
    private List<BookingInformation> bookingInformation;
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonIgnore
    private List<CommentEntity> comments;

    public User() {
    }

    public User(String Email, String password, Boolean isEnabled) {
        email = Email;
        this.password = password;
        this.isEnabled = isEnabled;
    }



}
