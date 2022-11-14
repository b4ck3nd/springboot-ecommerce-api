package com.kodlamiyoruz.ecomm.dto.user;


import lombok.Data;

@Data
public class CreditCardCreateRequestDto {
    private String cardNumber;
    private int ccv;
    private String nameAndSurname;
    private int expirationMonth;
    private int expirationYear;
    private int userId;

}
