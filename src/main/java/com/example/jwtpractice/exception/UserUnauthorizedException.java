package com.example.jwtpractice.exception;

public class UserUnauthorizedException extends RuntimeException{
    public UserUnauthorizedException (String message){
        super(message);
    }
}
