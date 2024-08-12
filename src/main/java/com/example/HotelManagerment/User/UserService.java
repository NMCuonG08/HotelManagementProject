package com.example.HotelManagerment.User;

import com.example.HotelManagerment.Exceptions.ResourceNotFoundException;
import com.example.HotelManagerment.Exceptions.UserAlreadyExitsException;
import com.example.HotelManagerment.Registration.RegistrationRequest;
import com.example.HotelManagerment.Registration.Token.VerificationToken;
import com.example.HotelManagerment.Registration.Token.VerificationTokenRepository;
import lombok.AllArgsConstructor;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class UserService implements IUserService{

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final VerificationTokenRepository verificationTokenRepository;

    @Override
    public User registerUser(RegistrationRequest reqest) {
        Optional<User> user = this.findByEmail(reqest.email());
        if(user.isPresent()) {
            throw  new UserAlreadyExitsException("User with email " + reqest.email() + " already exits");
        }
        User newUser = new User();
        newUser.setEmail(reqest.email());
        newUser.setPassword(passwordEncoder.encode(reqest.passWord()));
        newUser.setRole(reqest.role());
        newUser.setEnabled(false);
        return userRepository.save(newUser);
    }

    public UserResponse convertToUser(User user){
        UserResponse userResponse = new UserResponse();
        userResponse.setId(user.getId());
        userResponse.setEmail(user.getEmail());
        userResponse.setPassword(user.getPassword());
        userResponse.setHotel(user.getHotel());
        userResponse.setIsEnable(user.isEnabled());
        return userResponse;
    }

    @Override
    public Optional<User> findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public void saveUserVerificationToken(User user, String token) {
        var verificationToken = new VerificationToken(token, user);
        verificationTokenRepository.save(verificationToken);
    }

    @Override
    public String validateToken(String token) {
        VerificationToken theToken = verificationTokenRepository.findByToken(token);
        if(theToken == null ){
            return "Invalid Verification Token";
        }
        User user = theToken.getUser();
        Calendar calendar= Calendar.getInstance();
        if(theToken.getExpirationTime().getTime() - calendar.getTime().getTime() <= 0){
            verificationTokenRepository.delete(theToken);
            return "Token already expired!";
        }
        user.setEnabled(true);
        userRepository.save(user);
        return "valid";
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public User getUserById(Long userId) {
        return userRepository.findById(userId).orElseThrow(()-> new ResourceNotFoundException("User not found"));
    }


}
