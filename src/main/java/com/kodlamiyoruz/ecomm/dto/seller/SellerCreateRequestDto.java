package com.kodlamiyoruz.ecomm.dto.seller;


import lombok.Data;

@Data
public class SellerCreateRequestDto {
    private String name;
    private String email;
    private String password;

}
