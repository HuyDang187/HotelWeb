package com.hotelky4.projecthotel.model;

import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;

@Service
public class BookingManager {
    private static final String SESSION_KEY_BOOKINGORDER = "bookingOrder";
    public BookingCart getBookingCart(HttpSession session){
        BookingCart bookingCart = (BookingCart) session.getAttribute(SESSION_KEY_BOOKINGORDER);
        if (bookingCart != null){
            bookingCart = new BookingCart();
            setBookingCart(session,bookingCart);
        }
        return bookingCart;
    }
    public void setBookingCart(HttpSession session,BookingCart bookingCart){
        session.setAttribute(SESSION_KEY_BOOKINGORDER,bookingCart);
    }
    public void removeBookingCart(HttpSession session){
        session.removeAttribute(SESSION_KEY_BOOKINGORDER);
    }
}
