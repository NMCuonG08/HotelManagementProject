package com.example.HotelManagerment.TypeOfHotel;


import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public interface ITypeService {
    TypeOfHotel addNewType(String typeName, MultipartFile image) throws IOException, SQLException;

    List<TypeOfHotel> getALlTypeHotel();

    byte[] getImageByTypeId(Long id);
}
