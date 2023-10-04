package com.hotelky4.projecthotel.controller;

import com.hotelky4.projecthotel.entity.User;
import com.hotelky4.projecthotel.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/users")
public class UserController {
    private UserService userService;

    @Autowired
    private UserController(UserService theUserController){
        userService = theUserController;
    }

    @GetMapping("/admin/list")
    public String list(Model model){
        List<User> listUsers = userService.findAll();
        model.addAttribute("users",listUsers);
        return "/admin/user/list-user.html";
    }
    @RequestMapping("/admin/userdetail")
    public String userDetail(@RequestParam("userID")int theId, Model model){
        User theUser = userService.findById(theId);
        model.addAttribute("user",theUser);
        return "/admin/user/user-detail.html";
    }

}
