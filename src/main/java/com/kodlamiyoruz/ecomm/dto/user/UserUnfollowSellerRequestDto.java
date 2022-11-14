package com.kodlamiyoruz.ecomm.dto.user;


import lombok.Data;

@Data
public class UserUnfollowSellerRequestDto {
    private int userId;
    private int sellerId;
}
