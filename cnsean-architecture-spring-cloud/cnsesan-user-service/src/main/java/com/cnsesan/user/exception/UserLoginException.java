package com.cnsesan.user.exception;

public class UserLoginException extends RuntimeException {

    public UserLoginException(String message){
        super(message);
    }
}