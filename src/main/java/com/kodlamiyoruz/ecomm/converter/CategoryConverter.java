package com.kodlamiyoruz.ecomm.converter;


import com.kodlamiyoruz.ecomm.dto.category.CategoryCreateRequestDto;
import com.kodlamiyoruz.ecomm.dto.category.CategoryResponseDto;
import com.kodlamiyoruz.ecomm.model.Category;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class CategoryConverter {
    public CategoryResponseDto categoryToCategoryResponseDto(Category  category) {
        CategoryResponseDto dto=new CategoryResponseDto();
        dto.setCategoryName(category.getCategoryName());
        dto.setTotalProducts(category.getProducts().size());
        dto.setCategoryId(category.getCategoryId());
        return dto;
    }

    
    public Category categoryCreateDtoToCategory(CategoryCreateRequestDto dto) {
        Category category=new Category();
        category.setCategoryName(dto.getCategoryName());
        return category;
    }


    public List<CategoryResponseDto> categoryListToCategoryResponseDtoList(List<Category>  categories) {
        List<CategoryResponseDto> dtos=new ArrayList<>();
        categories.forEach(value -> {

            CategoryResponseDto dto=new CategoryResponseDto();
            dto.setCategoryName(value.getCategoryName());
            dto.setTotalProducts(value.getProducts().size());
            dto.setCategoryId(value.getCategoryId());
            dtos.add(dto);
        });
        return dtos;
    }




    
}
