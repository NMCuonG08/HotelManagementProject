package com.example.HotelManagerment.TypeOfHotel;

import com.example.HotelManagerment.Exceptions.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.sql.rowset.serial.SerialBlob;
import java.io.IOException;
import java.sql.Blob;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TypeService implements ITypeService {

    private final TypeRepository typeRepository;

    @Override
    public TypeOfHotel addNewType(String typeName, MultipartFile image) throws IOException, SQLException {
        TypeOfHotel type = new TypeOfHotel();
        type.setTypeName(typeName);
        if(!image.isEmpty()){
            byte[] imageByte =image.getBytes();
            Blob imageBlob = new SerialBlob(imageByte);
            type.setImage(imageBlob);
        }
        return typeRepository.save(type);
    }

    @Override
    public List<TypeOfHotel> getALlTypeHotel() {
        return typeRepository.findAll();
    }

    @Override
    public byte[] getImageByTypeId(Long id) {
        Optional<TypeOfHotel> theType = typeRepository.findById(id);
        if (theType.isPresent()) {
            Blob photo = theType.get().getImage();
            if (photo != null) {
                try {
                    int length = (int) photo.length();
                    if (length > 0) {
                        return photo.getBytes(1, length);
                    }
                } catch (SQLException e) {
                    // Handle the exception appropriately
                    e.printStackTrace(); // Example: Log the exception
                }
            }
        } else {
            throw new ResourceNotFoundException("Sorry, Room not found");
        }
        return null;
    }
}
