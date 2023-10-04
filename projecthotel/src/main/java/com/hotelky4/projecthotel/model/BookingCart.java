package com.hotelky4.projecthotel.model;

import com.hotelky4.projecthotel.entity.Room;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class BookingCart {
    private final List<BookingItem> items;
    private double total;
    public BookingCart(){
        items = new ArrayList<BookingItem>();
        total =0;
    }
    public BookingItem getItem(Room room){
        for (BookingItem item : items){
            if (item.getRoom().getId() == room.getId()){
                return item;
            }
        }return null;
    }
    public List<BookingItem> getItems(){
        return items;
    }
    public int getItemCount(){
        return items.size();
    }
    public void addItem(BookingItem item){
        addItem(item.getRoom(),item.getCheckinDate(),item.getCheckoutDate());
    }
    public void addItem(Room room , Date checkinDate , Date checkoutDate){
        BookingItem item = getItem(room);
        if (item != null){
            item.setCheckinDate(checkinDate);
            item.setCheckoutDate(checkoutDate);
            item.setQuantity(item.getQuantity());
        }else {
            item = new BookingItem(room,checkinDate,checkoutDate);
            item.setQuantity(item.getQuantity());
            items.add(item);
        }
    }
    public void updateItem(Room room,Date checkinDate , Date checkoutDate){
        BookingItem item = getItem(room);
        if (item!= null){
            item.setCheckinDate(checkinDate);
            item.setCheckoutDate(checkoutDate);
        }
    }
    public void removeItem(Room room){
        BookingItem item = getItem(room);
        if (item != null){
            items.remove(item);
        }
    }
    public void clear(){
        items.clear();
        total = 0;
    }
    public boolean isEmpty(){
        return items.isEmpty();
    }
}
