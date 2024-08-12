package com.example.HotelManagerment.TypeOfHotel;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.sql.Blob;

@Entity
@Setter
@Getter
public class TypeOfHotel {

    @Id
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    private Long id;
    @Column(name = "typeName")
    private String TypeName;
    @Column(name = "image")
    private Blob image;

}
