package com.example.HotelManagerment.User;

import com.example.HotelManagerment.HotelInformation.HotelInformation;
import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
@Data
@Getter
@Setter
public class UserResponse {
    private Long id;

    private String email;

    private String password;

    private Boolean isEnable;

    private HotelInformation hotel;

    public UserResponse() {
    }

    public UserResponse( String email, String password, Boolean isEnable) {
        this.email = email;
        this.password = password;
        this.isEnable = isEnable;
    }

    public UserResponse(String email, String password) {
        this.email = email;
        this.password = password;
    }
}
