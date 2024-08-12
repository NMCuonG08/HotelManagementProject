package com.example.HotelManagerment.User;

import com.example.HotelManagerment.Registration.RegistrationRequest;

import java.util.List;
import java.util.Optional;

public interface IUserService {
    public User registerUser(RegistrationRequest reqest);
    public UserResponse convertToUser(User user);
    public Optional<User> findByEmail(String email);

    void saveUserVerificationToken(User user, String verificationToken);

    String validateToken(String token);

    List<User> getAllUsers();

    User getUserById(Long userId);
}
