package com.example.HotelManagerment.RoomInformation;

import com.example.HotelManagerment.HotelInformation.HotelInformation;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Blob;

@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class RoomImages {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long ID;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="roomID")
    private RoomInformation room;
    @Lob
    @Column(name = "image")
    @JsonIgnore
    private Blob Image;
}
