package com.hotelky4.projecthotel.dao;

import com.hotelky4.projecthotel.entity.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


import java.util.Date;
import java.util.List;
@Repository
public interface RoomRepository extends JpaRepository<Room,Integer> {
    List<Room> findAll();
    List<Room> findByRoomTitleContainingIgnoreCaseAndRoomTypeContainingIgnoreCase(String titleRoomKey,String typeRoomKey);
    List<Room> findByRoomTitleContainingIgnoreCase(String titleRoomKey);
    List<Room> findByRoomTypeContainingIgnoreCase(String typeRoomKey);

    @Query("SELECT r FROM Room r WHERE r.id NOT IN (" +
            "SELECT b.room.id FROM Booking b " +
            "WHERE (b.checkin_date <= :checkoutDate AND b.checkout_date >= :checkinDate) " +
            "OR (b.checkin_date <= :checkinDate AND b.checkout_date >= :checkoutDate))")
    List<Room> findAvailableRooms(@Param("checkinDate") Date checkinDate, @Param("checkoutDate") Date checkoutDate);
//    List<Room> findByRoomAvailableGreaterThan(int available);
}
