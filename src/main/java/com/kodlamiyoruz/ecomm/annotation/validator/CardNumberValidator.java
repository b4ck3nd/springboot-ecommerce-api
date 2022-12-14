package com.kodlamiyoruz.ecomm.annotation.validator;

import com.kodlamiyoruz.ecomm.annotation.CardNumberValidate;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public  class CardNumberValidator implements ConstraintValidator<CardNumberValidate, String> {


    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        boolean a=!value.matches(".*[a-zA-Z]+.*");
        boolean b = value.length() == 12;
        return  a && b;
    }
}