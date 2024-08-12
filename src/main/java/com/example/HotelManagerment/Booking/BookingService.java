package com.example.HotelManagerment.Booking;

import com.example.HotelManagerment.Exceptions.BookingNotFound;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BookingService implements IBookingService {

    private final BookingRepository bookingRepository;

    @Override
    public void addBooking(BookingInformation bookingInformation) {
        bookingRepository.save(bookingInformation);
    }
    @Override
    public BookingResponse convertFromBookingInfor(BookingInformation bookingInformation){
        BookingResponse bookingResponse = new BookingResponse();
        bookingResponse.setId(bookingInformation.getId());
        bookingResponse.setCustomerName(bookingInformation.getCustomerName());
        bookingResponse.setEmail(bookingInformation.getEmail());
        bookingResponse.setPhoneNumber(bookingInformation.getPhoneNumber());
        bookingResponse.setCheckIn(bookingInformation.getCheckIn());
        bookingResponse.setCheckOut(bookingInformation.getCheckOut());
        bookingResponse.setBookingDate(bookingInformation.getBookingDate());
        bookingResponse.setTotalPrice(bookingInformation.getTotalPrice());
        bookingResponse.setIsCheckOut(bookingInformation.getIsCheckOut());
        bookingResponse.setBookingStatus(bookingInformation.getBookingStatus());
        bookingResponse.setUser(bookingInformation.getUser());
        bookingResponse.setRequired(bookingInformation.getRequired());
        bookingResponse.setHotelInformation(bookingInformation.getHotelInformation());
        if (bookingInformation.getPayment() != null) {
            bookingResponse.setPayment(bookingInformation.getPayment());
        }
        return bookingResponse;
    }

    @Override
    public List<BookingInformation> getALlBookingByUserID(Long id) {
        return bookingRepository.getALlBookingByUserID(id);
    }
    @Transactional
    @Override
    public BookingInformation changeBookingInfo(Long bookingId,String userName, String email, String phoneNumber, String required) {

        Optional<BookingInformation> bookingInformation =  bookingRepository.findById(bookingId);
        if(bookingInformation.isPresent()){
             bookingRepository.updateBookingInfo(bookingId,userName, email, phoneNumber, required);
            return bookingRepository.save(bookingInformation.get());
        }
        return null;
    }

    @Override
    public List<BookingInformation> getBookingsByHotelId(Long hotelId) {
        return bookingRepository.findByHotelId(hotelId);
    }
}
