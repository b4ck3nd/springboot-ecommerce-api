package com.kodlamiyoruz.ecomm.converter;


import com.kodlamiyoruz.ecomm.dto.user.UserCreateRequestDto;
import com.kodlamiyoruz.ecomm.dto.user.UserResponseDto;
import com.kodlamiyoruz.ecomm.dto.user.UserUpdateRequestDto;
import com.kodlamiyoruz.ecomm.model.User;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class UserConverter {
    public User userCreateRequestDtoDoUser(UserCreateRequestDto dto) {
        User user=new User();
        user.setUserName(dto.getUserName());
        user.setEmail(dto.getEmail());
        user.setPassword(dto.getPassword());
        return user;
    }


    public List<UserResponseDto> userListToToUserResponseDtoList(List<User> users) {
        List<UserResponseDto> dtos=new ArrayList<>();
        users.forEach(value -> {
            UserResponseDto dto=new UserResponseDto();
            dto.setUserName(value.getUserName());
            dto.setEmail(value.getEmail());
            dto.setCreatedDate(value.getCreatedDate().toString());
            dto.setUserId(value.getId());
            dtos.add(dto);
        });
        return dtos;
    }



    public UserResponseDto userToUserResponseDto(User user) {
        UserResponseDto dto=new UserResponseDto();
        dto.setUserName(user.getUserName());
        dto.setEmail(user.getEmail());
        dto.setCreatedDate(user.getCreatedDate().toString());
        dto.setUserId(user.getId());
        return dto;
    }
    public User userUpdateRequestDtoToUser(UserUpdateRequestDto dto) {
        User user=new User();
        user.setPassword(dto.getPassword());
        user.setUserName(dto.getUserName());
        user.setEmail(dto.getEmail());
        return user;
    }

}
