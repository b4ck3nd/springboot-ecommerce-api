package com.kodlamiyoruz.ecomm.controller;


import com.kodlamiyoruz.ecomm.dto.address.AddressResponseDto;
import com.kodlamiyoruz.ecomm.dto.order.OrderResponseDto;
import com.kodlamiyoruz.ecomm.dto.product.comment.ProductCommentResponseDto;
import com.kodlamiyoruz.ecomm.dto.user.*;
import com.kodlamiyoruz.ecomm.service.user.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value = "/api/user")
@Api(value = "User Controller")
public class UserController {

    @Autowired
    UserService userService;

    @GetMapping("/")
    @ApiOperation(value = "get all users",notes = "get all users",protocols = "Http-GET")
    public ResponseEntity<List<UserResponseDto>> getAll() {
        List<UserResponseDto> dtos=userService.findAll();
        return new ResponseEntity<>(dtos, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "get user by userid",notes = "get user by userid",protocols = "Http-GET")
    public ResponseEntity<UserResponseDto> findByUserId(@PathVariable int id) {
        UserResponseDto user=userService.findById(id);
        return new ResponseEntity<>(user,HttpStatus.OK);
    }
    @RequestMapping(value = "/delete/{id}",method = {RequestMethod.DELETE,RequestMethod.GET})
    @ApiOperation(value = "delete user by userid",notes = "delete user by userid",protocols = "Http-DELETE")
    public ResponseEntity<String> deleteByUserId(@PathVariable int id) {
        userService.deleteById(id);
        return new ResponseEntity<>("deleted",HttpStatus.OK);
    }

    @PostMapping("/add")
    @ApiOperation(value = "add a new user",notes = "add a new user",protocols = "Http-POST")
    public ResponseEntity<String> add(@RequestBody @Valid UserCreateRequestDto dto) {
        userService.add(dto);
        return new ResponseEntity<>("added",HttpStatus.OK);
    }

    @RequestMapping(value = "/update",method = {RequestMethod.GET,RequestMethod.PUT})
    @ApiOperation(value = "update a user",notes = "update a user",protocols = "Http-PUT")
    public ResponseEntity<UserResponseDto> updateByBy(@RequestBody UserUpdateRequestDto dto) {
        UserResponseDto user = userService.updateByUserId(dto);
        return new ResponseEntity<>(user,HttpStatus.OK);
    }


    @GetMapping("/{id}/comments")
    public ResponseEntity<List<ProductCommentResponseDto>> findProductCommentByUserId(@PathVariable int id) {
        List<ProductCommentResponseDto> comments = userService.findProductCommentsByUserId(id);
        return new ResponseEntity<>(comments,HttpStatus.OK);
    }

    @GetMapping("/{id}/creditcard")
    public ResponseEntity<List<CreditCardResponseDto>> findAllCreditCardByUserId(@PathVariable int id) {
        List<CreditCardResponseDto> dtos = userService.findAllCreditCardByUserId(id);
        return new ResponseEntity<>(dtos,HttpStatus.OK);
    }
    @PostMapping(value = "/creditcard/add")
    public ResponseEntity<String> addCreditCardToUser(@RequestBody CreditCardCreateRequestDto dto) {
        userService.addCreditCard(dto);
        return new ResponseEntity<>("added",HttpStatus.OK);
    }

    @GetMapping("/{userId}/creditcard/{creditCardId}")
    public ResponseEntity<CreditCardResponseDto> findCreditCardByUserIdAndCreditCardId(@PathVariable int userId,@PathVariable int creditCardId) {
        CreditCardResponseDto card = userService.findCreditCardByUserIdAndCreditCardId(userId, creditCardId);
        return new ResponseEntity<>(card,HttpStatus.OK);
    }

    //@GetMapping("/{id}/following")
    @RequestMapping(value = "/{id}/following",method = RequestMethod.GET)
    public ResponseEntity<List<UserFollowingSellerResponseDto>> findAllFollowingSeller(@PathVariable("id") int id) {
        List<UserFollowingSellerResponseDto> dtos = userService.followingSeller(Integer.parseInt(String.valueOf(id)));
        return new ResponseEntity<>(dtos,HttpStatus.OK);
    }

    @PostMapping("/following/add")
    public ResponseEntity<String> followSellerBySellerId(@RequestBody UserFollowSellerRequestDto dto) {
        userService.followSellerBySellerId(dto);
        return new ResponseEntity<>("seller following now",HttpStatus.OK);
    }

    @RequestMapping(value = "/following/remove",method = RequestMethod.DELETE)
    public ResponseEntity<String> removeFollowingSellerBySellerId(@RequestBody UserUnfollowSellerRequestDto dto) {
        userService.removeFollowingSeller(dto.getUserId(),dto.getSellerId());
        return new ResponseEntity<>("seller unfollowing",HttpStatus.OK);
    }

    @GetMapping("/{id}/orders")
    public ResponseEntity<List<OrderResponseDto>> findAllOrdersByUserId(@PathVariable int id) {
        List<OrderResponseDto> orders = userService.findAllOrdersByUserId(id);
        return new ResponseEntity<>(orders,HttpStatus.OK);
    }



}
