package com.kodlamiyoruz.ecomm.annotation.validator;


import com.kodlamiyoruz.ecomm.annotation.CardCcvValidate;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
public class CardCcvValidator implements ConstraintValidator<CardCcvValidate, String> {


    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {

        return value.length()>=3 && value.length()<=4;
    }
}