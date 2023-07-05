package com.example.jwtpractice.exception;

public class InvalidInputException extends RuntimeException{
    public InvalidInputException (String message){
        super(message);
    }
}
