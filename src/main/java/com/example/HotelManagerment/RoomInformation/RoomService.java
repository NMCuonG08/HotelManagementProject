package com.example.HotelManagerment.RoomInformation;

import com.example.HotelManagerment.Exceptions.InternalServerException;
import com.example.HotelManagerment.Exceptions.ResourceNotFoundException;
import com.example.HotelManagerment.Exceptions.RoomNotFoundException;
import com.example.HotelManagerment.HotelInformation.HotelInformation;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.sql.rowset.serial.SerialBlob;
import javax.sql.rowset.serial.SerialException;
import java.sql.Blob;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RoomService implements IRoomService{

    private final RoomRepository roomRepository;

    @Override
    public List<RoomInformation> getRoomByHotelID(Long hotelID) {
        return roomRepository.getRoomByHotelID(hotelID);
    }
    @Override
    public RoomResponse convertRoomToRoomResponse(RoomInformation roomInformation) {
        RoomResponse roomResponse = new RoomResponse();
        roomResponse.setID(roomInformation.getID());
        roomResponse.setRoomType(roomInformation.getRoomType());
        roomResponse.setRoomBed(roomInformation.getRoomBed());
        roomResponse.setPrice(roomInformation.getPrice());
        roomResponse.setStatus(roomInformation.getStatus());
        roomResponse.setRoomName(roomInformation.getRoomName());
        roomResponse.setCheckIn(roomInformation.getCheckIn());
        roomResponse.setCheckOut(roomInformation.getCheckOut());
        roomResponse.setClient(roomInformation.getClient());
        roomResponse.setSize(roomInformation.getSize());
        roomResponse.setHotelID(roomInformation.getHotel().getID());
        roomResponse.setSale(roomInformation.getSale());
        return roomResponse;
    }

    @Override
    public RoomInformation addRoomToHotel(RoomInformation roomInformation) {
        return roomRepository.save(roomInformation);
    }

    @Override
    public RoomInformation AddRoomImage(Long roomID, byte[] photoByte) {
        RoomInformation room = roomRepository.findById(roomID).orElseThrow(() -> new ResourceNotFoundException("Room not found"));
        if (photoByte != null && photoByte.length > 0){
            try{
                room.addImage(new SerialBlob(photoByte));
            }
            catch (Exception ex) {
                throw new InternalServerException("Error Adding Image ");
            }
        }
        return roomRepository.save(room);
    }

    @Override
    public List<String> getRoomImageByID(Long roomID) {
        List<Blob> roomImages = roomRepository.findHotelImageByID(roomID);
        List<String> results = new ArrayList<>();
        for (Blob roomImage : roomImages) {
            try {
                byte[] bytes = roomImage.getBytes(1, (int) roomImage.length());
                String base64String = Base64.getEncoder().encodeToString(bytes);
                results.add(base64String);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return results;
    }

    @Override
    public Optional<RoomInformation> getRoomById(Long roomID) {
        return roomRepository.findById(roomID);
    }

    @Override
    public RoomInformation addRoomImage(Long id, byte[] imageByte) {
        RoomInformation roomInformation = roomRepository.findById(id).orElseThrow(() -> new RoomNotFoundException("Room not found!"));
        if (imageByte != null && imageByte.length > 0) {
            try {
                roomInformation.addImage(new SerialBlob(imageByte));
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        return roomRepository.save(roomInformation);
    }

}
