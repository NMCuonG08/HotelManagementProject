package com.example.HotelManagerment.HotelInformation.HotelConvenient;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/convenient")
@RequiredArgsConstructor
@CrossOrigin("http://localhost:5173")
public class HotelConvenientController {

    private final IHotelConvenientService hotelConvenientService;
    @PostMapping("/{id}/add")
    public ResponseEntity<HotelConvenientResponse> addConvenient(@PathVariable Long id, @RequestBody List<String> convenient){
        HotelConvenient hotelConvenient = hotelConvenientService.addNewConvenient(id,convenient);
        HotelConvenientResponse response = hotelConvenientService.convertToHotelConvenientResponse(hotelConvenient);
        return ResponseEntity.ok(response);
    }
    @GetMapping("/{id}/all")
    public ResponseEntity<HotelConvenientResponse> getAllConvenient(@PathVariable Long id){
        HotelConvenient hotelConvenient = hotelConvenientService.getAllConvenient(id);
        HotelConvenientResponse response = hotelConvenientService.convertToHotelConvenientResponse(hotelConvenient);
        return ResponseEntity.ok(response);
    }
    @PutMapping("/{id}/update")
   public ResponseEntity<HotelConvenientResponse> updateConvenient(@PathVariable Long id,@RequestParam String type ,@RequestBody List<String> convenient){
        HotelConvenient hotelConvenient = hotelConvenientService.updateConvenient(id, type, convenient);
        HotelConvenientResponse response = hotelConvenientService.convertToHotelConvenientResponse(hotelConvenient);
        return ResponseEntity.ok(response);
   }

}
