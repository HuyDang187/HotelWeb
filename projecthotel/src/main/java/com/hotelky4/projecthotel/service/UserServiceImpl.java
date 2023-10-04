package com.hotelky4.projecthotel.service;

import com.hotelky4.projecthotel.dao.UserRepository;
import com.hotelky4.projecthotel.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class UserServiceImpl implements UserService {
    private UserRepository userRepository;
    @Autowired
    private UserServiceImpl(UserRepository theUserServiceImpl){
         userRepository = theUserServiceImpl;
    }

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public User findById(int theId) {
        Optional<User> result = userRepository.findById(theId);
        User theUser = null;
        if (result.isPresent()){
            theUser = result.get();

        }else {
            throw  new RuntimeException("Khong tim thay user voi id :"+theId);
        }

        return theUser;
    }

    @Override
    public void save(User theUser) {
        userRepository.save(theUser);
    }

    @Override
    public void deteleById(int theId) {
        userRepository.deleteById(theId);
    }

    @Override
    public User findByUsername(String theUsername){
        return userRepository.findByUsername(theUsername);

    }



}
