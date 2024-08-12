package com.example.HotelManagerment.HotelInformation.HotelConvenient;


import com.example.HotelManagerment.HotelInformation.HotelInformation;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Type;

import java.util.ArrayList;
import java.util.List;

@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class HotelConvenient {
    @Id
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    Long id ;

    private Long HotelID;

    @OneToMany(mappedBy = "hotelConvenient", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonIgnore
    private List<Convenient> convenients;

    public void addConvenient(String value){
        Convenient convenient = new Convenient();
        convenient.setName(value);
        convenient.setHotelConvenient(this);
        if (convenients== null){
            convenients =  new ArrayList<>();
        }
        convenients.add(convenient);
    }


    public HotelConvenient(Long hotelID) {
        HotelID = hotelID;
    }

   private String eating;

   private String childrenConvenient;

   private String entertainment;

   private String remoteWorking;

   private String service;

   private String facilities;

   private String language;

   private String disabilitiesPeople;


}
