package com.hotelky4.projecthotel.service;

import com.hotelky4.projecthotel.entity.Booking;

import java.util.List;

public interface BookingService {
    List<Booking> findAll();
    void save(Booking theBooking);
}
