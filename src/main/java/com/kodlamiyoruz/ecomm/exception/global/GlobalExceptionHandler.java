package com.kodlamiyoruz.ecomm.exception.global;


import com.kodlamiyoruz.ecomm.exception.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(value = {
            NotFoundException.class, CategoryException.class, SellerException.class,
            ProductException.class, CustomUserException.class, AddressException.class, OrderException.class,ProductCommentException.class
    })
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<String > notFoundExceptionHandler(RuntimeException exception) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(exception.getMessage());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    Map<String,String> handleAnnotationErrors(MethodArgumentNotValidException exception) {
        Map<String,String> errors=new HashMap<>();
       for (ObjectError error:exception.getBindingResult().getGlobalErrors()) {
           errors.put(error.getObjectName(),error.getDefaultMessage());
       }
        return errors;
    }


}
