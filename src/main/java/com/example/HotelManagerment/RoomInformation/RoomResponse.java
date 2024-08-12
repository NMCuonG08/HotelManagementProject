package com.example.HotelManagerment.RoomInformation;

import com.example.HotelManagerment.HotelInformation.HotelInformation;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Data
@AllArgsConstructor
@Getter
@Setter

public class RoomResponse {
    private Long ID;

    private String RoomType;

    private String RoomBed;

    private Double Price;

    private Double Sale;

    private String Status;

    private String RoomName;

    private LocalDate CheckIn;

    private LocalDate CheckOut;

    private List<String> ImageUrls;

    private Integer Client;

    private Double Size;
    private Long hotelID;

    public RoomResponse() {
    }

    public RoomResponse(String roomType, String roomBed, Double price,Double sale, String status, String roomName, LocalDate checkIn, LocalDate checkOut,Integer client, Double size, Long hotel) {
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
        this.hotelID = hotel;
    }
}
