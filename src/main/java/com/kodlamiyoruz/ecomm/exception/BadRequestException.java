package com.kodlamiyoruz.ecomm.exception;

public class BadRequestException extends RuntimeException {
    public BadRequestException() {
        super("bad request");
    }
}
