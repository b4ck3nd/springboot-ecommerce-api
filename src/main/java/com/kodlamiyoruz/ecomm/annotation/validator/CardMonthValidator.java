package com.kodlamiyoruz.ecomm.annotation.validator;

import com.kodlamiyoruz.ecomm.annotation.CardMonthValidate;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Date;

public class CardMonthValidator implements ConstraintValidator<CardMonthValidate,Integer> {

    @Override
    public boolean isValid(Integer value, ConstraintValidatorContext constraintValidatorContext) {

        return value >=new Date().getMonth();

    }
}
