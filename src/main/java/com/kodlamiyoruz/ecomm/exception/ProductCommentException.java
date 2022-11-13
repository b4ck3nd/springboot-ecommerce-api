package com.kodlamiyoruz.ecomm.exception;


public class ProductCommentException extends RuntimeException{
    public ProductCommentException(){super("not found any comments");}
    public ProductCommentException(int id) {super("not found any comments with this id: " + id);}
}
