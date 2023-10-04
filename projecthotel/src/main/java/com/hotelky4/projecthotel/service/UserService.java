package com.hotelky4.projecthotel.service;

import com.hotelky4.projecthotel.entity.User;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.List;
import java.util.Optional;

public interface UserService  {
    List<User> findAll();
    User findById(int theId);
    void save(User theUser);
    void deteleById(int theId);
    User findByUsername(String theUsername);
}
