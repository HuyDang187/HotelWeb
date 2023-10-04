package com.hotelky4.projecthotel.controller;

import com.hotelky4.projecthotel.entity.Booking;
import com.hotelky4.projecthotel.entity.Room;
import com.hotelky4.projecthotel.entity.User;
import com.hotelky4.projecthotel.service.BookingService;
import com.hotelky4.projecthotel.service.RoomService;
import com.hotelky4.projecthotel.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.expression.ParseException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Controller
@RequestMapping("/bookings")
public class BookingController {

    private BookingService bookingService;
    private RoomService roomService;
    private UserService userService;

    @Autowired
    private BookingController(BookingService theBookingController,RoomService roomService,UserService userService){
        bookingService=theBookingController;
        this.roomService = roomService;
        this.userService = userService;
    }

    @RequestMapping("/confirm")
    public String confirmBooking(@RequestParam("roomId") int roomId,
                                 @RequestParam("guestCount") int guestCount,
                                 @RequestParam("checkinDate") String checkinDate,
                                 @RequestParam("checkoutDate") String checkoutDate,
                                 Model model) {
        Room room = roomService.findById(roomId);
        SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication.isAuthenticated() && authentication.getPrincipal() instanceof UserDetails) {
            UserDetails userDetails = (UserDetails) authentication.getPrincipal();

            // Lấy thông tin về người dùng
            String username = userDetails.getUsername();
            User theUser = userService.findByUsername(username);
            // Sử dụng thông tin người dùng (username) trong xử lý đặt phòng
            // Xử lý thông tin đặt phòng ở đây (lưu vào cơ sở dữ liệu, gửi email xác nhận, ...)
            try{
                // Chuyển đổi chuỗi ngày sang kiểu java.util.Date
                Date checkinDateObj = sdf.parse(checkinDate);
                Date checkoutDateObj = sdf.parse(checkoutDate);

                // Tính số ngày đặt phòng
                long diffInMilliseconds = checkoutDateObj.getTime() - checkinDateObj.getTime();
                long diffInDays = TimeUnit.DAYS.convert(diffInMilliseconds, TimeUnit.MILLISECONDS);
                //tính tiền theo phòng
                double totalPrice = diffInDays * room.getPrice();


                Booking newBooking = new Booking();
                newBooking.setCheckin_date(checkinDateObj);
                newBooking.setCheckout_date(checkoutDateObj);
                newBooking.setRoom(room);
                newBooking.setUser(theUser);
                newBooking.setTotal_price(totalPrice);

                bookingService.save(newBooking);

                // Truyền thông tin đặt phòng đến trang xác nhận
                model.addAttribute("roomId", roomId);

                model.addAttribute("guestCount", guestCount);
                model.addAttribute("checkinDate", checkinDate);
                model.addAttribute("checkoutDate", checkoutDate);
                model.addAttribute("noDay", diffInDays);
                model.addAttribute("totalPrice", totalPrice);
            }catch (java.text.ParseException e) {
                e.printStackTrace();
            }
            return "booking-confirm"; // Chuyển hướng đến trang xác nhận đặt phòng
        }else {
            return "fancy-login.html";
        }







    }
    @GetMapping("/searchroom")
    public String showBookingForm(@RequestParam(name = "checkinDate") String checkinDate,
                                  @RequestParam(name = "checkoutDate") String checkoutDate, Model model) {
        SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
        try {
            // Chuyển đổi checkinDate và checkoutDate từ String sang kiểu Date
            Date checkinDateNew = sdf.parse(checkinDate);
            Date checkoutDateNew = sdf.parse(checkoutDate);

            // Tiếp tục xử lý với các biến kiểu Date
            List<Room> availableRooms = roomService.findAvailableRooms(checkinDateNew, checkoutDateNew);

            // Đưa danh sách phòng có sẵn vào model để hiển thị trong view
            model.addAttribute("rooms", availableRooms);

            // Hiển thị form đặt phòng với danh sách phòng có sẵn
            return "rooms.html";
        } catch (ParseException | java.text.ParseException e) {
            // Xử lý lỗi nếu có vấn đề với định dạng ngày tháng
            e.printStackTrace(); // Hoặc bạn có thể thêm xử lý khác tùy theo yêu cầu
            return "error-page"; // Trả về trang lỗi nếu có vấn đề với định dạng ngày tháng
        }
    }

}
