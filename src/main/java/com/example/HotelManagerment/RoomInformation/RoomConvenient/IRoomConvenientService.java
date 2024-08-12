package com.example.HotelManagerment.RoomInformation.RoomConvenient;

import java.util.List;

public interface IRoomConvenientService {
    RoomConvenient updateRoomConvenient(Long id, String type, List<String> convenient);

    RoomConvenientResponse convertToResponse(RoomConvenient roomConvenient);

    RoomConvenient getAllRoomConvenient(Long id);
}
