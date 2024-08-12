package com.example.HotelManagerment.Security.jwt;

import lombok.*;

@Data
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class JWTAuthenticationRequest {
    private String email;
    private String password;
}
