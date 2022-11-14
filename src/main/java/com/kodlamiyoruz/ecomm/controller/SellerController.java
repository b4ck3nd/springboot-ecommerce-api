package com.kodlamiyoruz.ecomm.controller;


import com.kodlamiyoruz.ecomm.dto.product.ProductResponseDto;
import com.kodlamiyoruz.ecomm.dto.seller.SellerCreateRequestDto;
import com.kodlamiyoruz.ecomm.dto.seller.SellerFollowerResponseDto;
import com.kodlamiyoruz.ecomm.dto.seller.SellerResponseDto;
import com.kodlamiyoruz.ecomm.dto.seller.SellerUpdateRequestDto;
import com.kodlamiyoruz.ecomm.dto.user.UserFollowingSellerResponseDto;
import com.kodlamiyoruz.ecomm.model.Product;
import com.kodlamiyoruz.ecomm.service.seller.SellerService;
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
@RequestMapping("/api/seller")
@Api("Seller Controller")
public class SellerController {

    @Autowired
    SellerService sellerService;

    @GetMapping("/")
    @ApiOperation(value = "get all sellers",notes = "get all sellers",protocols = "Http-GET")
    public ResponseEntity<List<SellerResponseDto>> getAll() {
        return new ResponseEntity<>(sellerService.findAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "get a seller by sellerid",notes = "get a seller by sellerid",protocols = "Http-GET")
    public ResponseEntity<SellerResponseDto> findById(@PathVariable int id) {
        SellerResponseDto sellerResponseDto=sellerService.findById(id);
        return new ResponseEntity<>(sellerResponseDto,HttpStatus.OK);
    }

    @PostMapping("/add")
    @ApiOperation(value = "add a new seller",notes = "add a new seller",protocols = "Http-POST")
    public ResponseEntity<Void> add(@RequestBody SellerCreateRequestDto sellerCreateRequestDto) {
        sellerService.add(sellerCreateRequestDto);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    //@DeleteMapping("/delete/{id}")
    @RequestMapping(value = "/delete/{id}",method = { RequestMethod.DELETE,RequestMethod.GET })
    @ApiOperation(value = "delete a seller by sellerid",notes = "update a seller by sellerid",protocols = "Http-DELETE")
    public ResponseEntity<String> deleteById(@PathVariable int id) {
        boolean status = sellerService.deleteById(id);
        if (status) {
            return new ResponseEntity<>("deleted",HttpStatus.OK);
        }
        else {
            return new ResponseEntity<>("not found any seller with this id: " + id , HttpStatus.OK);
        }
    }

    @RequestMapping(value = "/update",method = {RequestMethod.GET,RequestMethod.PUT})
    @ApiOperation(value = "update a seller by sellerid",notes = "update a seller by sellerid",protocols = "Http-PUT")
    public ResponseEntity<SellerResponseDto> updateSellerById(@RequestBody @Valid SellerUpdateRequestDto dto) {
        SellerResponseDto seller = sellerService.updateById(dto);
        return new ResponseEntity<>(seller,HttpStatus.OK);
    }

    @GetMapping("/{id}/products")
    @ApiOperation(value = "get all products by sellerid",notes = "get all products by sellerid",protocols = "Http-GET")
    public ResponseEntity<List<ProductResponseDto>> findAllProductsBySellerId(@PathVariable int id) {
        List<ProductResponseDto> all = sellerService.findAllProductsBySellerId(id);
        return new ResponseEntity<>(all,HttpStatus.OK);
    }

    @GetMapping("/{id}/followers")
    public ResponseEntity<List<SellerFollowerResponseDto>> findAllFollowerUserBySellerId(@PathVariable int id) {
        List<SellerFollowerResponseDto> followers = sellerService.findAllFollowerUserBySellerId(id);
        return new ResponseEntity<>(followers,HttpStatus.OK);
    }


}
