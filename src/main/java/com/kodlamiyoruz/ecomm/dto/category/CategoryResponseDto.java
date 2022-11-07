package com.kodlamiyoruz.ecomm.dto.category;


import lombok.Data;

@Data
public class CategoryResponseDto {
    private String categoryName;
    private int totalProducts;
    private int categoryId;
}
