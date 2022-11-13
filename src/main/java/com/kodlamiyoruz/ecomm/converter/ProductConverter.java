package com.kodlamiyoruz.ecomm.converter;


import com.kodlamiyoruz.ecomm.dto.product.ProductCreateRequestDto;
import com.kodlamiyoruz.ecomm.dto.product.ProductResponseDto;
import com.kodlamiyoruz.ecomm.model.Product;
import com.kodlamiyoruz.ecomm.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ProductConverter {

    @Autowired
    ProductRepository repository;
    public Product productCreateDtoToProduct(ProductCreateRequestDto dto) {
        Product p=new Product();
        p.setProductName(dto.getProductName());
        p.setProductBrand(dto.getProductBrand());
        p.setStock(dto.getStock());
        p.setProductPrice(dto.getProductPrice());

        return p;
    }

    public List<ProductResponseDto> productListToProductResponseDtoList(List<Product> products) {

        List<ProductResponseDto> dtos=new ArrayList<>();
       /*
        products.forEach( value -> {
            ProductResponseDto dto=new ProductResponseDto();
            dto.setProductId(value.getProductId());
            dto.setProductName(value.getProductName());
            dto.setProductBrand(value.getProductBrand());
            dto.setProductPrice(value.getProductPrice());
            dto.setStock(value.getStock());
            dto.setCategoryName(value.getCategory().getCategoryName());
            dtos.add(dto);
        });

        */
        for (Product p : products) {
            ProductResponseDto dto= new ProductResponseDto();
            dto.setProductId(p.getId());
            dto.setProductName(p.getProductName());
            dto.setProductBrand(p.getProductBrand());
            dto.setProductPrice(p.getProductPrice());
            dto.setStock(p.getStock());
            dto.setCategoryName(p.getCategory().getCategoryName());
            dto.setSellerName(p.getSeller().getName().trim());
            dtos.add(dto);
        }

        return dtos;
    }
    
    public ProductResponseDto productToProductResponseDto(Product product) {
        ProductResponseDto dto=new ProductResponseDto();
        dto.setProductId(product.getId());
        dto.setProductName(product.getProductName());
        dto.setProductBrand(product.getProductBrand());
        dto.setProductPrice(product.getProductPrice());
        dto.setStock(product.getStock());
        dto.setCategoryName(product.getCategory().getCategoryName());
        dto.setSellerName(product.getSeller().getName().trim());
        return dto;
    }


}
