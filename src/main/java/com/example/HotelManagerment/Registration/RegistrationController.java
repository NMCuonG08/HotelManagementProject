package com.example.HotelManagerment.Registration;


import com.example.HotelManagerment.Registration.Token.VerificationToken;
import com.example.HotelManagerment.Registration.Token.VerificationTokenRepository;
import com.example.HotelManagerment.User.User;
import com.example.HotelManagerment.User.UserService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.web.bind.annotation.*;

import java.net.http.HttpRequest;

@RestController
@AllArgsConstructor
@RequestMapping("/registration")
public class RegistrationController {
    private final UserService userService;
    private final ApplicationEventPublisher publisher;
    private final VerificationTokenRepository verificationTokenRepository;

    @PostMapping()
    public String registerUser(@RequestBody RegistrationRequest request, final HttpServletRequest httpServletRequest){
        User user = userService.registerUser(request);
        publisher.publishEvent(new RegistrationCompleteEvent(user,applicationUrl(httpServletRequest)));
        return "Success! please check your email for complete your registration!";
    }
    @GetMapping("/verifyEmail")
    public String verifyEmail(@RequestParam String token){
        VerificationToken verificationToken = verificationTokenRepository.findByToken(token);
        if(verificationToken.getUser().isEnabled()){
            return "This account has already been verified, please login! ";
        }
        String verificationResult = userService.validateToken(token);
        if(verificationResult.equalsIgnoreCase("valid")){
            return "Email verified successful!. Now you can login to your account.";
        }
        return "Invalid verification token.";
    }


    private String applicationUrl(HttpServletRequest request) {
        return "http://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath();
    }

}
