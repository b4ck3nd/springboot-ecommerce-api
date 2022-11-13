package com.kodlamiyoruz.ecomm.exception;

public class OrderException extends RuntimeException{
    public OrderException() {
        super("not found any order");
    }
    public OrderException(int id) {
        super("not found any order with this id: " + id);
    }


}
