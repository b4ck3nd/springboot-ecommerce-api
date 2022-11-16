package com.kodlamiyoruz.ecomm.annotation;

import com.kodlamiyoruz.ecomm.annotation.validator.CardNumberValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target( ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = CardNumberValidator.class)
public  @interface CardNumberValidate {
    String message() default "invalid cardnumber";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
