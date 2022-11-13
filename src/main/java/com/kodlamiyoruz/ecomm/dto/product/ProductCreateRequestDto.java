package com.kodlamiyoruz.ecomm.dto.product;


import lombok.Data;

@Data
public class ProductCreateRequestDto {
    private String productName;
    private String productBrand;
    private double productPrice;
    private int stock;
    private int categoryId;
    private int sellerId;

}
