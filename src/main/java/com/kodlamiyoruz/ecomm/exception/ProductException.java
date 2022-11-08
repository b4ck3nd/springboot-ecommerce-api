package com.kodlamiyoruz.ecomm.exception;

public class ProductException extends RuntimeException{
    public ProductException(){
        super("not found any products");
    }
    public ProductException(int id) {
        super("not found any product with this id: " + id);
    }
    public ProductException(String productName) {
        super("not found any product with this name: " + productName );
    }

}
