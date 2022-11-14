package com.kodlamiyoruz.ecomm.dto.user;


import lombok.Data;

@Data
public class UserFollowSellerRequestDto {
    private int userId;
    private int sellerId;
}
