package com.example.jwtpractice.service;

import com.example.jwtpractice.entity.User;
import com.example.jwtpractice.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

        @Autowired
        private UserRepository userRepository;

    public List<User>getAllUsers(){
        return userRepository.findAll();
    }

    public User getUserByEmail(String email){
        return userRepository.getByEmail(email).orElseThrow();
    }

    public User getUserById(int id){
        return userRepository.findById(id).orElseThrow();
    }

}
