package com.kodlamiyoruz.ecomm.service.category;

import com.kodlamiyoruz.ecomm.dto.category.CategoryCreateRequestDto;
import com.kodlamiyoruz.ecomm.dto.category.CategoryResponseDto;

import java.util.List;

public interface CategoryService {
    CategoryResponseDto findByCategoryName(String categoryName);
    List<CategoryResponseDto> findAll();
    CategoryResponseDto findById(int id);
    void add(CategoryCreateRequestDto categoryCreateRequestDto);
    String deleteById(int id);

}
