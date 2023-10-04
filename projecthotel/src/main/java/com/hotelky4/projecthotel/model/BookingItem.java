package com.hotelky4.projecthotel.model;

import com.hotelky4.projecthotel.entity.Room;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class BookingItem {
    private final Room room;
    private Date checkinDate;
    private Date checkoutDate;
    private long quantity;
    private double subtotal ;

    public BookingItem(Room room,Date checkinDate,Date checkoutDate)  {
        this.room = room;
        this.checkinDate = checkinDate;
        this.checkoutDate = checkoutDate;
        this.quantity = noDate(checkinDate,checkoutDate);
        this.subtotal = room.getPrice()*noDate(checkinDate,checkoutDate);
    }

    public Room getRoom() {
        return room;
    }

    public long getQuantity() {
        return quantity;
    }



    public double getSubtotal() {
        return subtotal = room.getPrice() * quantity;
    }

    public void setSubtotal(double subtotal) {
        this.subtotal = subtotal;
    }

    public static long noDate(Date checkinDate,Date checkoutDate) {


        // Tính số ngày chênh lệch
        long diffInMillies = Math.abs(checkoutDate.getTime() - checkinDate.getTime());
        long diff = TimeUnit.DAYS.convert(diffInMillies, TimeUnit.MILLISECONDS);


        return diff;
    }

    public Date getCheckinDate() {
        return checkinDate;
    }

    public void setCheckinDate(Date checkinDate) {
        this.checkinDate = checkinDate;
    }

    public Date getCheckoutDate() {
        return checkoutDate;
    }

    public void setCheckoutDate(Date checkoutDate) {
        this.checkoutDate = checkoutDate;
    }

    public void setQuantity(long quantity) {
        this.quantity = quantity;
    }
}
