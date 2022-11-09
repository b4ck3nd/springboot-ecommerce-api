package com.kodlamiyoruz.ecomm.exception;

public class CustomUserException extends RuntimeException{
    public CustomUserException() {
        super("not found any user");
    }
    public CustomUserException(int id) {
        super("not found any user with this id: " + id);
    }
    public CustomUserException(String userName) {
        super("not found any user with this username: " + userName);
    }
    public  CustomUserException(String userName,String email) {
        super(email + " email address already used by another user");
    }
}
