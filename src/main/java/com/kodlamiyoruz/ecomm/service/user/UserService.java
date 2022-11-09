package com.kodlamiyoruz.ecomm.service.user;

import com.kodlamiyoruz.ecomm.dto.user.UserCreateRequestDto;
import com.kodlamiyoruz.ecomm.dto.user.UserResponseDto;
import com.kodlamiyoruz.ecomm.dto.user.UserUpdateRequestDto;

import java.util.List;

public interface UserService {
    void add(UserCreateRequestDto dto);
    List<UserResponseDto> findAll();
    UserResponseDto findById(int id);
    void deleteById(int id);
    UserResponseDto findByUserName(String userName);
    UserResponseDto findByEmail(String email);
    UserResponseDto updateByUserId(UserUpdateRequestDto dto);

}
