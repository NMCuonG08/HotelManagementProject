package com.example.HotelManagerment.RoomInformation;

import com.example.HotelManagerment.HotelInformation.HotelConvenient.HotelConvenient;
import com.example.HotelManagerment.HotelInformation.HotelImage;
import com.example.HotelManagerment.HotelInformation.HotelInformation;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.relational.core.sql.In;

import java.sql.Blob;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class RoomInformation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long ID;

    @Column(name = "room_type")
    private String RoomType;

    @Column(name = "room_bed")
    private String RoomBed;

    @Column(name = "price")
    private Double Price;
    @Column(name = "sale")
    private Double Sale;


    @Column(name = "status")
    private String Status;

    @Column(name = "room_name")
    private String RoomName;

    @Column(name = "check_in")
    private LocalDate CheckIn;

    @Column(name = "check_out")
    private LocalDate CheckOut;
    @OneToMany(mappedBy = "room", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JsonIgnore
    private List<RoomImages> images;

    @Column(name = "clients")
    private Integer Client;

    @Column(name = "size")
    private Double Size;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="hotelID")
    @JsonIgnore
    private HotelInformation hotel;


    public RoomInformation(String roomType, String roomBed, Double price,Double sale , String status, String roomName, LocalDate checkIn, LocalDate checkOut,  Integer client, Double size, HotelInformation hotel) {
        RoomType = roomType;
        RoomBed = roomBed;
        Price = price;
        Status = status;
        RoomName = roomName;
        CheckIn = checkIn;
        CheckOut = checkOut;
        Client = client;
        Size = size;
        Sale = sale;
        this.hotel = hotel;
    }

    public void addImage(Blob imageBlob){
        RoomImages image = new RoomImages();
        image.setImage(imageBlob);
        image.setRoom(this);
        if (images == null) {
            images = new ArrayList<>();
        }
        images.add(image);
    }




}
