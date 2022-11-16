package com.kodlamiyoruz.ecomm.dto.user;


import lombok.Data;

@Data
public class CreditCardResponseDto {
    private int userId;
    private int creditCardId;
    private String userName;
    private int expirationMonth;
    private int expirationYear;
    private String createdDate;

}
