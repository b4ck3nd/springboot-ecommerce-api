package com.kodlamiyoruz.ecomm.service.user;

import com.kodlamiyoruz.ecomm.dto.product.comment.ProductCommentResponseDto;
import com.kodlamiyoruz.ecomm.dto.user.*;

import java.util.List;

public interface UserService {
    void add(UserCreateRequestDto dto);
    List<UserResponseDto> findAll();
    UserResponseDto findById(int id);
    void deleteById(int id);
    UserResponseDto findByUserName(String userName);
    UserResponseDto findByEmail(String email);
    UserResponseDto updateByUserId(UserUpdateRequestDto dto);
    List<ProductCommentResponseDto> findProductCommentsByUserId(int id);
    void addCreditCard(CreditCardCreateRequestDto dto);
    void deleteCreditCardById(int id);
    List<CreditCardResponseDto> findAllCreditCardByUserId(int id);
    CreditCardResponseDto findCreditCardByUserIdAndCreditCardId(int userId,int creditCardId);

    void followSellerBySellerId(UserFollowSellerRequestDto dto);
    List<UserFollowingSellerResponseDto> followingSeller(int id);
    void removeFollowingSeller(int userId,int sellerId);

}
