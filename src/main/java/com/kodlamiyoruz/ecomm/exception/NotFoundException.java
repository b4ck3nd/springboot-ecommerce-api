package com.kodlamiyoruz.ecomm.exception;

public class NotFoundException extends RuntimeException{
    public NotFoundException() {
        super("not found");
    }
    public NotFoundException(String name) {
        super(name);
    }
    public NotFoundException(int id) {
        super("not found with this id: " + id);
    }
}
