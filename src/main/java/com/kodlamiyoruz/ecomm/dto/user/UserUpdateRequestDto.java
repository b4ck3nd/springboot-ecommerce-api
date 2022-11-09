package com.kodlamiyoruz.ecomm.dto.user;


import lombok.Data;

@Data
public class UserUpdateRequestDto {
    private int userId;
    private String userName;
    private String email;
    private String password;
}
