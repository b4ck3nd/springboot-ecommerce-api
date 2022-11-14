package com.kodlamiyoruz.ecomm.dto.product;


import lombok.Data;

@Data
public class ProductInCartResponseDto {
    private String productName;
    private String productBrand;
    private double productPrice;
    private int quantity;
    private int stock;
    private int userId;
    private int addressId;
    private int cartId;

}
