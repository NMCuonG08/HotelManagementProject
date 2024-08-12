package com.example.HotelManagerment.User;

import com.example.HotelManagerment.Registration.RegistrationRequest;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping()
@AllArgsConstructor
public class UserController {
    private final IUserService userService;

    @GetMapping()
    public ResponseEntity<List<UserResponse>> getAllUsers(){
        List<User> users =  userService.getAllUsers();
        List<UserResponse> userResponses = new ArrayList<>();
        for(User user : users) {
            userResponses.add(userService.convertToUser(user));
        }
        return ResponseEntity.ok(userResponses);
    }

}
