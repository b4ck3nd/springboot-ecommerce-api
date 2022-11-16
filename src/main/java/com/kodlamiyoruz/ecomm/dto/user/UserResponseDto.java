package com.kodlamiyoruz.ecomm.dto.user;


import lombok.Data;

@Data
public class UserResponseDto {
    private String userName;
    private String email;
    private int userId;
    private String createdDate;
}
