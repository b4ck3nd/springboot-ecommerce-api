package com.kodlamiyoruz.ecomm.exception.global;


import com.kodlamiyoruz.ecomm.exception.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(value = {NotFoundException.class, CategoryException.class, SellerException.class, ProductException.class, CustomUserException.class})
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<String > notFoundExceptionHandler(RuntimeException exception) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(exception.getMessage());
    }
}
