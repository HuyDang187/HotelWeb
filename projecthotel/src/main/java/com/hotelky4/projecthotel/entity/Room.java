package com.hotelky4.projecthotel.entity;

import javax.persistence.*;

@Entity
@Table(name = "rooms")
public class Room {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "title")
    private  String roomTitle;

    @Column(name = "type")
    private String roomType;

    @Column(name = "description")
    private String roomDescription;

    @Column(name = "room_img")
    private String roomImg;

    @Column(name = "guest_id")
    private int roomGuest;

    @Column(name = "available_room")
    private int roomAvailable;

    @Column(name = "per_one_night")
    private double price;

    @Column(name = "max")
    private String roomMax;

    @Column(name = "size")
    private String roomSize;

    @Column(name = "bed")
    private int roomBed;

    public Room() {
    }


    public Room(int id, String roomTitle, String roomType, String roomDescription, String roomImg, int roomGuest, int roomAvailable, double price, String roomMax, String roomSize, int roomBed) {
        this.id = id;
        this.roomTitle = roomTitle;
        this.roomType = roomType;
        this.roomDescription = roomDescription;
        this.roomImg = roomImg;
        this.roomGuest = roomGuest;
        this.roomAvailable = roomAvailable;
        this.price = price;
        this.roomMax = roomMax;
        this.roomSize = roomSize;
        this.roomBed = roomBed;
    }

    public String getRoomMax() {
        return roomMax;
    }

    public void setRoomMax(String roomMax) {
        this.roomMax = roomMax;
    }

    public String getRoomSize() {
        return roomSize;
    }

    public void setRoomSize(String roomSize) {
        this.roomSize = roomSize;
    }

    public int getRoomBed() {
        return roomBed;
    }

    public void setRoomBed(int roomBed) {
        this.roomBed = roomBed;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getRoomTitle() {
        return roomTitle;
    }

    public void setRoomTitle(String roomTitle) {
        this.roomTitle = roomTitle;
    }

    public String getRoomType() {
        return roomType;
    }

    public void setRoomType(String roomType) {
        this.roomType = roomType;
    }

    public String getRoomDescription() {
        return roomDescription;
    }

    public void setRoomDescription(String roomDescription) {
        this.roomDescription = roomDescription;
    }

    public String getRoomImg() {
        return roomImg;
    }

    public void setRoomImg(String roomImg) {
        this.roomImg = roomImg;
    }

    public int getRoomGuest() {
        return roomGuest;
    }

    public void setRoomGuest(int roomGuest) {
        this.roomGuest = roomGuest;
    }

    public int getRoomAvailable() {
        return roomAvailable;
    }

    public void setRoomAvailable(int roomAvailable) {
        this.roomAvailable = roomAvailable;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
