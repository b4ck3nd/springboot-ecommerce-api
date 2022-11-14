package com.kodlamiyoruz.ecomm.annotation.validator;

import com.kodlamiyoruz.ecomm.annotation.CardNumberExpiration;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public  class CardNumberExpirationValidator implements ConstraintValidator<CardNumberExpiration, String> {

    @Override
    public void initialize(CardNumberExpiration annotation) {

    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        boolean a=!value.matches(".*[a-zA-Z]+.*");
        boolean b = value.length() == 12;
        return  a && b;
    }
}