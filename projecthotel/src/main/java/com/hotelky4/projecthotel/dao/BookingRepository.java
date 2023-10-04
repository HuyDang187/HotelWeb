package com.hotelky4.projecthotel.dao;

import com.hotelky4.projecthotel.entity.Booking;
import com.hotelky4.projecthotel.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookingRepository extends JpaRepository<Booking,Integer> {
    List<Booking> findAll();
    List<User> findByUser_id(int theUserId);

}
