package com.example.HotelManagerment.TypeOfHotel;

import jakarta.persistence.Lob;
import lombok.*;
import org.apache.tomcat.util.codec.binary.Base64;

import java.sql.Blob;

@Data
@Setter
@Getter
@NoArgsConstructor

public class TypeResponse {
    private Long id ;
    private String TypeName;
    @Lob
    private String image;

    public TypeResponse(Long id ,String typeName, byte[] imageByte) {
        this.id = id;
        TypeName = typeName;
        this.image = imageByte != null ? Base64.encodeBase64String(imageByte) : null;
    }
}
