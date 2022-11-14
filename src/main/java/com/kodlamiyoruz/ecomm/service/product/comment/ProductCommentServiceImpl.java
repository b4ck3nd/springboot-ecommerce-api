package com.kodlamiyoruz.ecomm.service.product.comment;


import com.kodlamiyoruz.ecomm.converter.ProductCommentConverter;
import com.kodlamiyoruz.ecomm.dto.product.comment.ProductCommentCreateRequestDto;
import com.kodlamiyoruz.ecomm.dto.product.comment.ProductCommentResponseDto;
import com.kodlamiyoruz.ecomm.exception.CustomUserException;
import com.kodlamiyoruz.ecomm.exception.ProductCommentException;
import com.kodlamiyoruz.ecomm.exception.ProductException;
import com.kodlamiyoruz.ecomm.model.Product;
import com.kodlamiyoruz.ecomm.model.ProductComment;
import com.kodlamiyoruz.ecomm.model.User;
import com.kodlamiyoruz.ecomm.repository.ProductCommentRepository;
import com.kodlamiyoruz.ecomm.repository.ProductRepository;
import com.kodlamiyoruz.ecomm.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductCommentServiceImpl implements ProductCommentService{

    @Autowired
    ProductCommentRepository commentRepository;
    @Autowired
    ProductCommentConverter commentConverter;
    @Autowired
    ProductRepository productRepository;

    @Autowired
    UserRepository userRepository;


    @Override
    public void addCommentToProduct(ProductCommentCreateRequestDto dto) {
        if (!productRepository.existsById(dto.getProductId())) {
            throw new ProductException(dto.getProductId());
        }
        if (!userRepository.existsById(dto.getUserId())) {
            throw new CustomUserException(dto.getUserId());
        }
        User user = userRepository.findById(dto.getUserId()).get();
        Product product = productRepository.findById(dto.getProductId()).get();
        ProductComment comment = commentConverter.productCommentCreateRequestDtoToProductComment(dto, user);
        List<ProductComment> commentList=new ArrayList<>();
        commentList.add(comment);
        comment.setProduct(product);
        product.setProductComments(commentList);
        user.setProductComment(commentList);
        commentRepository.save(comment);
        productRepository.save(product);
        userRepository.save(user);

    }

    @Override
    public void deleteById(int id) {
        if (commentRepository.existsById(id)) {
            commentRepository.deleteById(id);
        }
        else {
            throw new ProductCommentException(id);
        }
    }

    @Override
    public ProductCommentResponseDto findById(int id) {
        if(commentRepository.existsById(id)) {
            ProductComment comment = commentRepository.findById(id).get();
            return commentConverter.productCommentToProductCommentResponseDto(comment);
        }
        else {
            throw new ProductCommentException(id);
        }
    }

    @Override
    public List<ProductCommentResponseDto> findAll() {
        List<ProductCommentResponseDto> dtos=new ArrayList<>();
        /*
        for (ProductComment comment : commentRepository.findAll()) {
            dtos.add(commentConverter.productCommentToProductCommentResponseDto(comment));
        }
        return dtos;
        */
        /*
        commentRepository.findAll().stream().distinct().forEach(value -> {
            dtos.add(commentConverter.productCommentToProductCommentResponseDto(value));
        });
        return dtos;
        */
        List<ProductComment> all = commentRepository.findAll();
        List<ProductCommentResponseDto> dtos1 = commentConverter.productCommentListToProductCommentDtoList(all);
        return dtos1;

    }
}
