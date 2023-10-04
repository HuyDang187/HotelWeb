package com.hotelky4.projecthotel.service;

import com.hotelky4.projecthotel.entity.Room;

import java.util.Date;
import java.util.List;

public interface RoomService {
    List<Room> findAll();
    Room findById(int theId);
    void save(Room theRoom);
    void deleteById(int theId);
    List<Room> searchRoomsByTitleAndType(String titleRoomKey,String typeRoomKey);
    List<Room> findAvailableRooms(Date checkinDate, Date checkoutDate);
}
