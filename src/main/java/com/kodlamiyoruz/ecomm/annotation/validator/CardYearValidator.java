package com.kodlamiyoruz.ecomm.annotation.validator;


import com.kodlamiyoruz.ecomm.annotation.CardYearValidate;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Date;

public class CardYearValidator implements ConstraintValidator<CardYearValidate,Integer> {

    @Override
    public boolean isValid(Integer value, ConstraintValidatorContext constraintValidatorContext) {
        return new Date().getYear() <= value;
    }
}
