package com.kodlamiyoruz.ecomm.service.user;


import com.kodlamiyoruz.ecomm.converter.ProductCommentConverter;
import com.kodlamiyoruz.ecomm.converter.UserConverter;
import com.kodlamiyoruz.ecomm.dto.product.comment.ProductCommentResponseDto;
import com.kodlamiyoruz.ecomm.dto.user.UserCreateRequestDto;
import com.kodlamiyoruz.ecomm.dto.user.UserResponseDto;
import com.kodlamiyoruz.ecomm.dto.user.UserUpdateRequestDto;
import com.kodlamiyoruz.ecomm.exception.CustomUserException;
import com.kodlamiyoruz.ecomm.model.ProductComment;
import com.kodlamiyoruz.ecomm.model.User;
import com.kodlamiyoruz.ecomm.repository.UserRepository;
import com.kodlamiyoruz.ecomm.service.product.comment.ProductCommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    UserConverter userConverter;

    @Autowired
    ProductCommentService commentService;
    @Autowired
    ProductCommentConverter commentConverter;

    @Override
    public void add(UserCreateRequestDto dto) {
        if (userRepository.existsByEmail(dto.getEmail())) {
            throw new CustomUserException(dto.getUserName(),dto.getEmail());
        }

        User user=userConverter.userCreateRequestDtoDoUser(dto);
        userRepository.save(user);
    }

    @Override
    public List<UserResponseDto> findAll() {
        return userConverter.userListToToUserResponseDtoList(userRepository.findAll());
    }


    @Override
    public UserResponseDto findById(int id) {
        Optional<User> get = userRepository.findById(id);
        if (!(get.isPresent())) {
            throw new CustomUserException(id);
        }
        return userConverter.userToUserResponseDto(get.get());
    }

    @Override
    public void deleteById(int id) {
        if (!(userRepository.existsById(id))) {
            throw new CustomUserException(id);
        }
        userRepository.deleteById(id);
    }

    @Override
    public UserResponseDto findByUserName(String userName) {
        User byUserName = userRepository.findByUserName(userName);
        /*
        if (byUserName.equals("null") || byUserName == null) {
            throw new CustomUserException(userName);
        }
        */
        if (Objects.isNull(byUserName)) {
            throw new CustomUserException(userName);
        }

        return userConverter.userToUserResponseDto(byUserName);
    }

    @Override
    public UserResponseDto findByEmail(String email) {
        User byEmail = userRepository.findByEmail(email);
        /*
        if ( (byEmail.equals("null") || byEmail == null)) {
            throw new CustomUserException(email);
        }
        */
        if (Objects.isNull(byEmail)) {
            throw new CustomUserException(email);
        }

        return userConverter.userToUserResponseDto(byEmail);
    }

    @Override
    public UserResponseDto updateByUserId(UserUpdateRequestDto dto) {
        if ( !( userRepository.existsById(dto.getUserId()))) {
            throw new CustomUserException(dto.getUserId());
        }
        User user=userConverter.userUpdateRequestDtoToUser(dto);
        userRepository.save(user);
        return userConverter.userToUserResponseDto(user);
    }

    @Override
    public List<ProductCommentResponseDto> findProductCommentsByUserId(int id) {
        if (!userRepository.existsById(id)) {
            throw new CustomUserException(id);
        }
        User user = userRepository.findById(id).get();
        List<ProductComment> productComment = user.getProductComment();
        return  commentConverter.productCommentListToProductCommentDtoList(productComment);
    }

    /*
    @PostConstruct
    private void test() {
        User user=new User();
        user.setUserName("test isimli kisi");
        user.setEmail("testisimlikisi@kisi.com");
        user.setPassword("passwordneymisbe");
        userRepository.save(user);

    }


     */


}
