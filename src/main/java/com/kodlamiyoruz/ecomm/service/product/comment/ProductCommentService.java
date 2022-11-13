package com.kodlamiyoruz.ecomm.service.product.comment;

import com.kodlamiyoruz.ecomm.dto.product.comment.ProductCommentCreateRequestDto;
import com.kodlamiyoruz.ecomm.dto.product.comment.ProductCommentResponseDto;

import java.util.List;

public interface ProductCommentService {
    void addCommentToProduct(ProductCommentCreateRequestDto dto);
    void deleteById(int id);
    ProductCommentResponseDto findById(int id);
    List<ProductCommentResponseDto> findAll();
}
