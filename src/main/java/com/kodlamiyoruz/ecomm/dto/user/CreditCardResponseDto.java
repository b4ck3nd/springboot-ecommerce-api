package com.kodlamiyoruz.ecomm.dto.user;


import lombok.Data;

@Data
public class CreditCardResponseDto {
    private int userId;
    private String userName;
    private int creditCardId;
    private int expirationMonth;
    private int expirationYear;

}
