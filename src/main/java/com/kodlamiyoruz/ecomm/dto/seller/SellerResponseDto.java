package com.kodlamiyoruz.ecomm.dto.seller;


import lombok.Data;

@Data
public class SellerResponseDto {
    private String name;
    private int totalProducts;
    private String createdDate;
    private int sellerId;

}
