package com.hotelky4.projecthotel.entity;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "bookings")
public class Booking {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    @Column(name = "guest_name")
    private String guest_name;
    @Column(name = "guest_phonenumber")
    private int guest_phone;
    @Column(name = "guest_email")
    private String guest_email;
    @Column(name = "checkin_date")
    private Date checkin_date;
    @Column(name = "checkout_date")
    private Date checkout_date;
    @Column(name = "total_price")
    private double total_price;
    @Column(name = "status")
    private String status;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
    @ManyToOne
    @JoinColumn(name = "room_id")
    private Room room;
    public Booking() {
    }

    public Booking(int id, Date checkin_date, Date checkout_date, double total_price, String status, User user, Room room,String guest_name,String guest_email,int guest_phone) {
        this.id = id;
        this.checkin_date = checkin_date;
        this.checkout_date = checkout_date;
        this.total_price = total_price;
        this.status = status;
        this.user = user;
        this.room = room;
        this.guest_name = guest_name;
        this.guest_email = guest_email;
        this.guest_phone = guest_phone;

    }

    public String getGuest_name() {
        return guest_name;
    }

    public void setGuest_name(String guest_name) {
        this.guest_name = guest_name;
    }

    public int getGuest_phone() {
        return guest_phone;
    }

    public void setGuest_phone(int guest_phone) {
        this.guest_phone = guest_phone;
    }

    public String getGuest_email() {
        return guest_email;
    }

    public void setGuest_email(String guest_email) {
        this.guest_email = guest_email;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getCheckin_date() {
        return checkin_date;
    }

    public void setCheckin_date(Date checkin_date) {
        this.checkin_date = checkin_date;
    }

    public Date getCheckout_date() {
        return checkout_date;
    }

    public void setCheckout_date(Date checkout_date) {
        this.checkout_date = checkout_date;
    }

    public double getTotal_price() {
        return total_price;
    }

    public void setTotal_price(double total_price) {
        this.total_price = total_price;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Room getRoom() {
        return room;
    }

    public void setRoom(Room room) {
        this.room = room;
    }
}
