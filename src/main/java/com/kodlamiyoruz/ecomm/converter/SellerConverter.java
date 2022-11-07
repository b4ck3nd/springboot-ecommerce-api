package com.kodlamiyoruz.ecomm.converter;

import com.kodlamiyoruz.ecomm.dto.seller.SellerCreateRequestDto;
import com.kodlamiyoruz.ecomm.dto.seller.SellerResponseDto;
import com.kodlamiyoruz.ecomm.model.Seller;
import com.kodlamiyoruz.ecomm.repository.SellerRepository;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class SellerConverter {

    public Seller sellerCreateRequestDtoToSeller(SellerCreateRequestDto dto) {
        return  new Seller(dto.getName(), dto.getEmail());
    }

    public List<SellerResponseDto> sellerListToSellerResponseDtoList(List<Seller> sellers) {
        List<SellerResponseDto> dtos=new ArrayList<>();
        sellers.forEach((value) -> {
            SellerResponseDto dto=new SellerResponseDto();
            dto.setName(value.getName());
            dto.setCreatedDate(value.getCreatedDate().toString());
            dto.setTotalProducts(value.getProducts().size());
            dto.setSellerId(value.getSellerId());
            dtos.add(dto);

        });
        return dtos;
    }
    public SellerResponseDto sellerToSellerResponseDto(Seller seller) {
        SellerResponseDto dto=new SellerResponseDto();
        dto.setCreatedDate(seller.getCreatedDate().toString());
        dto.setName(seller.getName());
        dto.setTotalProducts(seller.getProducts().size());
        dto.setSellerId(seller.getSellerId());
        return dto;
    }


}
