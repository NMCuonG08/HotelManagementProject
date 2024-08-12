package com.example.HotelManagerment.RoomInformation;

import java.util.List;
import java.util.Optional;

public interface IRoomService {

    List<RoomInformation> getRoomByHotelID(Long hotelID);

    RoomResponse convertRoomToRoomResponse(RoomInformation roomInformation);

    RoomInformation addRoomToHotel(RoomInformation roomInformation);

    RoomInformation AddRoomImage(Long roomID, byte[] photoByte);

    List<String> getRoomImageByID(Long roomID);

    Optional<RoomInformation> getRoomById(Long roomID);

    RoomInformation addRoomImage(Long id, byte[] imageByte);
}
