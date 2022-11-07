package com.kodlamiyoruz.ecomm.service.seller;

import com.kodlamiyoruz.ecomm.dto.seller.SellerCreateRequestDto;
import com.kodlamiyoruz.ecomm.dto.seller.SellerResponseDto;

import java.util.List;

public interface SellerService {
    void add(SellerCreateRequestDto sellerCreateRequestDto);
    boolean deleteById(int id);
    List<SellerResponseDto> findAll();
    SellerResponseDto findById(int id);

}
