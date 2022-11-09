package com.kodlamiyoruz.ecomm.dto.user;

import lombok.Data;

@Data
public class UserCreateRequestDto {
    private String userName;
    private String password;
    private String email;
}
