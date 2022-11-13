package com.kodlamiyoruz.ecomm.service.seller;

import com.kodlamiyoruz.ecomm.dto.product.ProductResponseDto;
import com.kodlamiyoruz.ecomm.dto.seller.SellerCreateRequestDto;
import com.kodlamiyoruz.ecomm.dto.seller.SellerResponseDto;
import com.kodlamiyoruz.ecomm.dto.seller.SellerUpdateRequestDto;
import com.kodlamiyoruz.ecomm.model.Product;

import java.util.List;
import java.util.ListIterator;

public interface SellerService {
    void add(SellerCreateRequestDto sellerCreateRequestDto);
    boolean deleteById(int id);
    List<SellerResponseDto> findAll();
    SellerResponseDto findById(int id);
    SellerResponseDto updateById(SellerUpdateRequestDto dto);
    List<ProductResponseDto> findAllProductsBySellerId(int id);

}
