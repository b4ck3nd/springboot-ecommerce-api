package com.kodlamiyoruz.ecomm.dto.seller;


import lombok.Data;

@Data
public class SellerUpdateRequestDto {
    private int sellerId;
    private String name;
    private String email;

}
