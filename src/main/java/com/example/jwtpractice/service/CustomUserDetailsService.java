package com.example.jwtpractice.service;

import com.example.jwtpractice.entity.CustomUserDetails;
import com.example.jwtpractice.entity.User;
import com.example.jwtpractice.exception.ResourceNotFoundException;
import com.example.jwtpractice.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Component
public class CustomUserDetailsService implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> optionalUser = userRepository.getByEmail(username);
        User user = optionalUser.orElse(null); // Set a default value (null in this case) or handle it differently if desired
        if (user == null) {
            throw new UsernameNotFoundException("User Not Found..!");
        }
        CustomUserDetails customUserDetails = new CustomUserDetails(user);
        return customUserDetails;
    }
}
