package com.example.HotelManagerment.TypeOfHotel;


import com.example.HotelManagerment.Exceptions.PhotoRetrievalException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.sql.Blob;
import java.sql.SQLException;
import java.util.ArrayList;
import org.apache.tomcat.util.codec.binary.Base64;
import java.util.List;

@CrossOrigin("http://localhost:5173")
@RestController
@RequestMapping("/type-of-hotel")
@RequiredArgsConstructor
public class TypeController {
    private final ITypeService typeService;
    @PostMapping("/add")
    public ResponseEntity<TypeResponse> addNewTypeHotel(
            @RequestParam String TypeName,
            @RequestParam MultipartFile image) throws SQLException, IOException {
        TypeOfHotel type = typeService.addNewType(TypeName, image);
        TypeResponse typeResponse = getTypeResponse(type);
        return ResponseEntity.ok(typeResponse);
    }
    @GetMapping("/all-type")
    public ResponseEntity<List<TypeResponse>> getALlType(){
        List<TypeOfHotel> hotels = typeService.getALlTypeHotel();
         List<TypeResponse> typeResponses = new ArrayList<>();
       for(TypeOfHotel type : hotels ) {
           TypeResponse typeResponse = getTypeResponse(type);
           byte[] photoByte = typeService.getImageByTypeId(typeResponse.getId());
           if (photoByte != null && photoByte.length > 0 ) {
               String BasePhoto = Base64.encodeBase64String(photoByte);
               typeResponse.setImage(BasePhoto);
               typeResponses.add(typeResponse);
           }
       }
       return ResponseEntity.ok(typeResponses);
    }

    public TypeResponse getTypeResponse(TypeOfHotel type){
        byte[] photoByte = null;
        Blob photoBlob = type.getImage();
        if (photoBlob != null ) {
            try {
                photoByte = photoBlob.getBytes(1,(int) photoBlob.length());
            }
            catch  (SQLException e) {
                throw new PhotoRetrievalException("Error Photo here");
            }
        }
        return new TypeResponse(type.getId(),
                type.getTypeName(),
                photoByte
        );
    }


}
