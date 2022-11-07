package com.kodlamiyoruz.ecomm.exception;

public class SellerException extends RuntimeException{
    public SellerException() {
        super("not found any seller.can you add a new seller");
    }
    public SellerException(String msg) {
        super(msg);
    }
    public SellerException(int id) {
        super("not found any seller with this id: " + id);
    }
    public SellerException(int id,String msg){
        super("id: " + id + " or " + msg + " incorrect.");
    }
}
