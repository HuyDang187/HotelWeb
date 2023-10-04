package com.hotelky4.projecthotel.service;

import com.hotelky4.projecthotel.dao.RoomRepository;
import com.hotelky4.projecthotel.entity.Room;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class RoomServiceImpl implements RoomService {

    private RoomRepository roomRepository ;

    @Autowired
    public RoomServiceImpl(RoomRepository theRoomRepository){

         roomRepository=theRoomRepository;
    }

    @Override
    public List<Room> findAll() {
        return roomRepository.findAll();
    }

    @Override
    public Room findById(int theId) {
        Optional<Room> result = roomRepository.findById(theId);
        Room theRoom = null;
        if (result.isPresent()) {
            theRoom = result.get();

        } else {
            throw new RuntimeException("Did not find the Room id - " + theId);
        }
        return theRoom;
    }

    @Override
    public void save(Room theRoom) {
            roomRepository.save(theRoom);
    }

    @Override
    public void deleteById(int theId) {
            roomRepository.deleteById(theId);
    }
    @Override
    public List<Room> searchRoomsByTitleAndType(String titleRoomKey,String typeRoomKey){
        if(StringUtils.hasText(titleRoomKey) && StringUtils.hasText(typeRoomKey)){
            return roomRepository.findByRoomTitleContainingIgnoreCaseAndRoomTypeContainingIgnoreCase(titleRoomKey,typeRoomKey);
        }else if (StringUtils.hasText(typeRoomKey)){
            return roomRepository.findByRoomTypeContainingIgnoreCase(typeRoomKey);
        }else if (StringUtils.hasText(titleRoomKey)) {
            return roomRepository.findByRoomTitleContainingIgnoreCase(titleRoomKey);
        }else {
            return Collections.emptyList();
        }
    }
    @Override
    public List<Room> findAvailableRooms(Date checkinDate, Date checkoutDate) {
        return roomRepository.findAvailableRooms(checkinDate, checkoutDate);
    }

//    @Override
//    public List<Room> getRoomAvailable(int available) {
//        return roomRepository.findByRoomAvailableGreaterThan(0);
//    }
}
