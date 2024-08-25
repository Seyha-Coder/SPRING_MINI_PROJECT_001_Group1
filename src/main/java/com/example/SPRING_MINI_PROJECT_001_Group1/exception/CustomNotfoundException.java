package com.example.SPRING_MINI_PROJECT_001_Group1.exception;

public class CustomNotfoundException extends  RuntimeException{
    public CustomNotfoundException(String message){
        super(message);
    }
}
