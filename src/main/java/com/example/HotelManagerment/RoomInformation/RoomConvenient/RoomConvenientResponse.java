package com.example.HotelManagerment.RoomInformation.RoomConvenient;

import lombok.*;

@Data
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor

public class RoomConvenientResponse {

     private Long id;

    private Long roomID;

    private String eating;

    private String bedRoom;

    private String bathRoom;

    private String entertainment;

    private String internet;

    private String balcony;

    private String other;


}
