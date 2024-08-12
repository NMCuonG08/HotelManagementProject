package com.example.HotelManagerment.Booking;

import com.example.HotelManagerment.HotelInformation.HotelInformation;
import com.example.HotelManagerment.HotelInformation.IHotelService;
import com.example.HotelManagerment.Payment.PaymentSaveData.IPaymentService;
import com.example.HotelManagerment.Payment.PaymentSaveData.PaymentEntity;
import com.example.HotelManagerment.User.IUserService;
import com.example.HotelManagerment.User.User;
import com.example.HotelManagerment.User.UserRepository;
import com.example.HotelManagerment.User.UserService;
import lombok.RequiredArgsConstructor;
import org.apache.catalina.connector.Response;
import org.springframework.cglib.core.Local;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@CrossOrigin("http://localhost:5173")
@RestController
@RequiredArgsConstructor
@RequestMapping("/booking")
public class BookingController {
    private final IBookingService bookingService;
    private final UserRepository userRepository;
    private final IHotelService hotelService;
    private final IPaymentService paymentService;
    @PostMapping("/add-booking")
    public ResponseEntity<BookingResponse> addBooking(@RequestParam String customerName,
                                                      @RequestParam String email,
                                                      @RequestParam String phoneNumber,
                                                      @RequestParam LocalDate checkIn,
                                                      @RequestParam LocalDate checkOut,
                                                      @RequestParam Double totalPrice,
                                                      @RequestParam String userEmail,
                                                      @RequestParam String required,
                                                      @RequestParam Long hotelId,
                                                      @RequestParam(required = false) Long paymentId
                                                      ){
        Optional<User> user = userRepository.findByEmail(userEmail);
        Optional<HotelInformation> hotelInformation = hotelService.getHotelByID(hotelId);
        PaymentEntity payment = null;
        if (paymentId != null ) {
            paymentService.getPaymentById(paymentId);
        }
        BookingResponse response = null;
        BookingInformation bookingInformation = null;
        if(user.isPresent() && hotelInformation.isPresent() ){
            bookingInformation = new BookingInformation(customerName,email,phoneNumber,checkIn,checkOut, LocalDate.now(), totalPrice,false,"pending",required,user.get(), hotelInformation.get(), payment);
            bookingService.addBooking(bookingInformation);
        }
        else {
            throw  new UsernameNotFoundException("User not found");
        }
        response = bookingService.convertFromBookingInfor(bookingInformation);
        return ResponseEntity.ok(response);
    }
    @GetMapping("/yourBooking")
    public ResponseEntity<List<BookingResponse>> showAllBookingByUserEmail(@RequestParam String userEmail){

        Optional<User> user = userRepository.findByEmail(userEmail);
        List<BookingResponse>  bookingResponses= new ArrayList<>();
        List<BookingInformation> bookings = new ArrayList<>();
        if (user.isPresent()){
            bookings = bookingService.getALlBookingByUserID(userRepository.findByEmail(userEmail).get().getId());
            if(bookings != null) {
                for (BookingInformation bookingInformation : bookings) {
                    bookingResponses.add(bookingService.convertFromBookingInfor(bookingInformation));
                }
            }
        }
        else {
            throw new UsernameNotFoundException("User not found");
        }
        return ResponseEntity.ok(bookingResponses);
    }
    @PutMapping("/update/{bookingId}")
    public ResponseEntity<String> changeBookingInfo(
            @PathVariable Long bookingId,
            @RequestParam String userName,
            @RequestParam String email,
            @RequestParam String phoneNumber,
            @RequestParam String required
    ){
            BookingResponse bookingResponse = null;
            BookingInformation bookingInformation = bookingService.changeBookingInfo(bookingId,userName,email, phoneNumber, required);
            bookingResponse =   bookingService.convertFromBookingInfor(bookingInformation);
            return ResponseEntity.ok("Update Successfully!");
    }
    @GetMapping("/get-bookings/{hotelId}")
    public ResponseEntity<List<BookingResponse>> getBookingsByHotelId(@PathVariable Long hotelId){
        List<BookingInformation> bookingInformations = bookingService.getBookingsByHotelId(hotelId);
        List<BookingResponse> bookingResponses = bookingInformations.stream().map(
                        bookingService::convertFromBookingInfor).
                collect(Collectors.toList());
        return ResponseEntity.ok(bookingResponses);
    }


}




