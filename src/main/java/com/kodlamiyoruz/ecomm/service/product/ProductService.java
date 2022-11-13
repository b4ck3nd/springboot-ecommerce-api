package com.kodlamiyoruz.ecomm.service.product;

import com.kodlamiyoruz.ecomm.dto.product.ProductCreateRequestDto;
import com.kodlamiyoruz.ecomm.dto.product.ProductResponseDto;
import com.kodlamiyoruz.ecomm.dto.product.ProductUpdateRequestDto;
import com.kodlamiyoruz.ecomm.dto.product.comment.ProductCommentResponseDto;

import java.util.List;

public interface ProductService {
    boolean add(ProductCreateRequestDto productCreateRequestDto);
    ProductResponseDto findById(int id);
    List<ProductResponseDto> findByProductName(String productName);
    List<ProductResponseDto> findByProductBrand(String productBrand);
    ProductResponseDto updateByProductId(ProductUpdateRequestDto productUpdateRequestDto);
    List<ProductResponseDto> findAll();
    void deleteById(int id);
    List<ProductCommentResponseDto> findProductCommentsByProductId(int id);
    List<ProductResponseDto> searchProductByProductName(String productName);

    List<ProductResponseDto> searchProductByProductBrand(String productBrand);

}
