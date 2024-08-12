package com.example.HotelManagerment.RoomInformation;


import com.example.HotelManagerment.Exceptions.InternalServerException;
import com.example.HotelManagerment.Exceptions.RoomNotFoundException;
import com.example.HotelManagerment.HotelInformation.HotelInformation;
import com.example.HotelManagerment.HotelInformation.HotelResponse;
import com.example.HotelManagerment.HotelInformation.IHotelService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
@CrossOrigin("http://localhost:5173")
@RestController
@RequiredArgsConstructor
@RequestMapping("/rooms")

public class RoomController {
    private final IRoomService roomService;
    private final IHotelService hotelService;
    @GetMapping("/{hotelID}/room-information")
    public ResponseEntity<List<RoomResponse>> getRoomByHotelID(@PathVariable Long hotelID){
        List<RoomInformation> roomInformations = roomService.getRoomByHotelID(hotelID);

        List<RoomResponse> roomResponse = new ArrayList<>();
        for( RoomInformation room : roomInformations ){
            roomResponse.add(roomService.convertRoomToRoomResponse(room));
        }
        return ResponseEntity.ok(roomResponse);
    }

    @PostMapping("/{HotelID}/add-room")
    public ResponseEntity<RoomResponse> AddRoomToHotel(@PathVariable("HotelID") Long HotelID,
                               @RequestParam("RoomType") String RoomType,
                               @RequestParam("RoomBed") String RoomBed,
                               @RequestParam("Price") Double Price,
                               @RequestParam("Status") String Status,
                               @RequestParam("RoomName") String RoomName,
                               @RequestParam("CheckIn") LocalDate CheckIn,
                               @RequestParam("CheckOut") LocalDate CheckOut,
                               @RequestParam("Clients") Integer Clients,
                               @RequestParam("Size") Double Size,
                               @RequestParam("images") List<MultipartFile> images) throws IOException {
        Optional<HotelInformation> hotelInformation = hotelService.getHotelByID(HotelID);
        RoomInformation roomInformation = new RoomInformation(RoomType,RoomBed,Price,0.0,Status,RoomName,CheckIn,CheckOut,Clients,Size,hotelInformation.get());
        RoomInformation theRoom =  roomService.addRoomToHotel(roomInformation);
         RoomResponse roomResponse = roomService.convertRoomToRoomResponse(theRoom);
         Long roomID = roomInformation.getID();
        for (MultipartFile photo : images) {
            byte[] photoByte = photo != null && !photo.isEmpty() ? photo.getBytes() : null;
            roomInformation = roomService.AddRoomImage(roomID, photoByte);
        }
        ResponseEntity.ok().build();
       return ResponseEntity.ok(roomResponse);
    }
    @GetMapping("/{roomID}/images")
    public List<String> getRoomImagesByRoomID(@PathVariable Long roomID){
        return  roomService.getRoomImageByID(roomID);

    }
    @GetMapping("/roomInformation/{roomID}")
    public ResponseEntity<RoomResponse> getRoomByID(@PathVariable Long roomID){
        Optional<RoomInformation> theRoom = roomService.getRoomById(roomID);
        RoomResponse room = null;
        if (theRoom.isPresent()){
             room = roomService.convertRoomToRoomResponse(theRoom.get());
        }
        else {
            throw new RoomNotFoundException("Room not found");
        }
        return ResponseEntity.ok(room);
    }
    @PostMapping("/add-images/{id}")
    public ResponseEntity<Void> addRoomImages(
            @PathVariable Long id,
            @RequestParam List<MultipartFile> images
    ) throws IOException {
        Optional<RoomInformation> roomInformationOptional = roomService.getRoomById(id);
        if (roomInformationOptional.isPresent()){
            RoomInformation room = roomInformationOptional.get();
            for (MultipartFile image : images) {
                byte[] imageByte = image != null && !image.isEmpty() ? image.getBytes() : null;
                room = roomService.addRoomImage(id,imageByte);
            }
            return ResponseEntity.ok().build();
        }
        else {
            return  ResponseEntity.notFound().build();
        }
    }

}
