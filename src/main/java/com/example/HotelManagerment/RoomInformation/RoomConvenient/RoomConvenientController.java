package com.example.HotelManagerment.RoomInformation.RoomConvenient;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@CrossOrigin("http://localhost:5173")
@RequestMapping("/room-convenient")
public class RoomConvenientController {

    private final IRoomConvenientService roomConvenientService;

    @PutMapping("/{id}/update")
    public ResponseEntity<RoomConvenientResponse> updateRoomConvenient(@PathVariable Long id,@RequestParam String type,@RequestBody List<String> convenient ){
        RoomConvenient roomConvenient = roomConvenientService.updateRoomConvenient(id, type, convenient);
        RoomConvenientResponse response = roomConvenientService.convertToResponse(roomConvenient);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}/all")
    public ResponseEntity<RoomConvenientResponse> getAllRoomConvenient(@PathVariable Long id){
        RoomConvenient roomConvenient = roomConvenientService.getAllRoomConvenient(id);
        return ResponseEntity.ok(roomConvenientService.convertToResponse(roomConvenient));
    }


}
