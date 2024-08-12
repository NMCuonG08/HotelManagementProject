package com.example.HotelManagerment.RoomInformation.RoomConvenient;

import com.example.HotelManagerment.Exceptions.RoomNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;


@Service
@RequiredArgsConstructor

public class RoomConvenientService implements IRoomConvenientService {


    private final RoomConvenientRepository roomConvenientRepository;

    @Override
    public RoomConvenient updateRoomConvenient(Long id, String type, List<String> convenient) {
        RoomConvenient roomConvenient = roomConvenientRepository.findByRoomID(id).orElseGet(() -> new RoomConvenient(id));

        List<String> newConvenientList = new ArrayList<>(convenient);

        switch (type){
            case "eating" :
                roomConvenient.setEating(updateConvenientField(roomConvenient.getEating(), newConvenientList));
                break;
            case "bedRoom" :
                roomConvenient.setBedRoom(updateConvenientField(roomConvenient.getBedRoom(), newConvenientList));
                break;
            case "bathRoom" :
                roomConvenient.setBathRoom(updateConvenientField(roomConvenient.getBathRoom(), newConvenientList));
                break;
            case "entertainment" :
                roomConvenient.setEntertainment(updateConvenientField(roomConvenient.getEntertainment(), newConvenientList));
                break;
            case "internet" :
                roomConvenient.setInternet(updateConvenientField(roomConvenient.getInternet(), newConvenientList));
                break;
            case "balcony" :
                roomConvenient.setBalcony(updateConvenientField(roomConvenient.getBalcony(), newConvenientList));
                break;
            case "other" :
                roomConvenient.setOther(updateConvenientField(roomConvenient.getOther() , newConvenientList));
                break;

        }

        roomConvenientRepository.save(roomConvenient);
        return roomConvenient;
    }

    @Override
    public RoomConvenientResponse convertToResponse(RoomConvenient roomConvenient) {
        RoomConvenientResponse response = new RoomConvenientResponse();
        response.setId(roomConvenient.getId());
        response.setEating(roomConvenient.getEating());
        response.setBedRoom(roomConvenient.getBedRoom());
        response.setBathRoom(roomConvenient.getBathRoom());
        response.setEntertainment(roomConvenient.getEntertainment());
        response.setRoomID(roomConvenient.getRoomID());
        response.setBalcony(roomConvenient.getBalcony());
        response.setInternet(roomConvenient.getInternet());
        response.setOther(roomConvenient.getOther());
        return response;
    }

    @Override
    public RoomConvenient getAllRoomConvenient(Long id) {
        Optional<RoomConvenient> roomConvenient = roomConvenientRepository.findByRoomID(id);
        if (roomConvenient.isPresent()){
            return roomConvenient.get();
        }
        else throw new RoomNotFoundException("Room Convenient not found");
    }

    private String updateConvenientField(String existingConvenient, List<String> newConvenientList) {
        if (existingConvenient == null || existingConvenient.isEmpty()) {
            return String.join(", ", newConvenientList);
        } else {
            List<String> existingList = new ArrayList<>(Arrays.asList(existingConvenient.split(", ")));


            for (String item : newConvenientList) {
                if (!existingList.contains(item)) {
                    existingList.add(item);
                }
            }

            // Remove items not in new list
            existingList.removeIf(item -> !newConvenientList.contains(item));

            return String.join(", ", existingList);
        }
    }
}
