package com.example.HotelManagerment.RoomInformation.RoomConvenient;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor

public class RoomConvenient {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long roomID;

    private String eating;

    private String bedRoom;

    private String bathRoom;

    private String entertainment;

    private String internet;

    private String balcony;

    private String other;

    public RoomConvenient(Long roomID) {
        this.roomID = roomID;
    }
}
