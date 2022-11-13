package com.kodlamiyoruz.ecomm.dto.product.comment;

import lombok.Data;

@Data
public class ProductCommentResponseDto {
    private String title;
    private String body;
    private double rating;
    private String userName;
    private int productCommentId;
    private int productId;
    private String productName;
    private String productBrand;
    private String createdDate;
}
