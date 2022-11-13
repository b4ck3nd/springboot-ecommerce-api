package com.kodlamiyoruz.ecomm.dto.product.comment;

import lombok.Data;

@Data
public class ProductCommentCreateRequestDto {
    private String title;
    private String body;
    private double rating;
    private int productId;
    private int userId;
}
