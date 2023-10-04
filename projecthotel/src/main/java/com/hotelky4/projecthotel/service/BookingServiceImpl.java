package com.hotelky4.projecthotel.service;

import com.hotelky4.projecthotel.dao.BookingRepository;
import com.hotelky4.projecthotel.dao.RoomRepository;
import com.hotelky4.projecthotel.dao.UserRepository;
import com.hotelky4.projecthotel.entity.Booking;
import com.hotelky4.projecthotel.entity.Room;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class BookingServiceImpl implements BookingService{
     private RoomRepository roomRepository;
     private UserRepository userRepository;
     private BookingRepository bookingRepository;

     @Autowired
     public BookingServiceImpl(RoomRepository theRoomRepository,UserRepository theUserRepository,BookingRepository theBookingRepository  ){
         roomRepository=theRoomRepository;
         userRepository=theUserRepository;
         bookingRepository=theBookingRepository;
     }
     @Override
    public List<Booking> findAll(){
       return bookingRepository.findAll();
     }

    @Override
    public void save(Booking theBooking) {
        bookingRepository.save(theBooking);
    }
}
