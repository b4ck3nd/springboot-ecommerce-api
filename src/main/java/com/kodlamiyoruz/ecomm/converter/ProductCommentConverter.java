package com.kodlamiyoruz.ecomm.converter;


import com.kodlamiyoruz.ecomm.dto.product.comment.ProductCommentCreateRequestDto;
import com.kodlamiyoruz.ecomm.dto.product.comment.ProductCommentResponseDto;
import com.kodlamiyoruz.ecomm.model.ProductComment;
import com.kodlamiyoruz.ecomm.model.User;
import org.springframework.stereotype.Component;
import com.kodlamiyoruz.ecomm.model.Product;

import java.util.ArrayList;
import java.util.List;

@Component
public class ProductCommentConverter {
    public ProductComment productCommentCreateRequestDtoToProductComment(ProductCommentCreateRequestDto dto, User user) {
        ProductComment comment=new ProductComment();
        comment.setRating(dto.getRating());
        comment.setBody(dto.getBody());
        comment.setTitle(dto.getTitle());
        comment.setUser(user);
        return comment;
    }

    public ProductCommentResponseDto productCommentToProductCommentResponseDto(ProductComment comment) {
        ProductCommentResponseDto dto=new ProductCommentResponseDto();
        dto.setProductCommentId(comment.getId());
        dto.setBody(comment.getBody());
        dto.setTitle(comment.getTitle());
        dto.setRating(comment.getRating());
        dto.setProductBrand(comment.getProduct().getProductBrand());
        dto.setProductName(comment.getProduct().getProductName());
        dto.setProductId(comment.getProduct().getId());
        dto.setProductCommentId(comment.getId());
        dto.setCreatedDate(comment.getCreatedDate().toString());
        dto.setUserName(comment.getUser().getUserName());
        return dto;
    }

    public List<ProductCommentResponseDto> productCommentListToProductCommentDtoList(List<ProductComment> comments) {
        List<ProductCommentResponseDto> dtos=new ArrayList<>();
        comments.forEach(value -> {
            ProductCommentResponseDto dto=new ProductCommentResponseDto();
            dto.setProductId(value.getId());
            dto.setTitle(value.getTitle());
            dto.setBody(value.getBody());
            dto.setProductBrand(value.getProduct().getProductBrand());
            dto.setProductName(value.getProduct().getProductName());
            dto.setProductId(value.getProduct().getId());
            dto.setRating(value.getRating());
            dto.setProductCommentId(value.getId());
            dto.setCreatedDate(value.getCreatedDate().toString());
            dto.setUserName(value.getUser().getUserName());
            dtos.add(dto);
        });
        return dtos;
    }



}
