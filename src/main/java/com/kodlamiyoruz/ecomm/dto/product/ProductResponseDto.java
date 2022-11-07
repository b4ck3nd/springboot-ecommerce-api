package com.kodlamiyoruz.ecomm.dto.product;


import lombok.Data;

@Data
public class ProductResponseDto {
    private String productName;
    private String productBrand;
    private double productPrice;
    private int stock;
    private int productId;
    private String categoryName;
    private String sellerName;

}
