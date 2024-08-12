package com.example.HotelManagerment.HotelInformation.HotelConvenient;

import com.example.HotelManagerment.HotelInformation.HotelInformation;
import lombok.*;

import java.util.List;

@Data
@Setter
@Getter
public class HotelConvenientResponse {
    private Long id;
    private Long HotelID;
    private List<Convenient> convenients;
    private String eating;

    private String childrenConvenient;

    private String entertainment;

    private String remoteWorking;

    private String service;

    private String facilities;

    private String language;

    private String disabilitiesPeople;
    public HotelConvenientResponse() {
    }

}
