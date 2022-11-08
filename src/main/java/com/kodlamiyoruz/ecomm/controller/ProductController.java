package com.kodlamiyoruz.ecomm.controller;


import com.kodlamiyoruz.ecomm.dto.product.ProductCreateRequestDto;
import com.kodlamiyoruz.ecomm.dto.product.ProductResponseDto;
import com.kodlamiyoruz.ecomm.dto.product.ProductUpdateRequestDto;
import com.kodlamiyoruz.ecomm.service.product.ProductService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/product")
@Api
public class ProductController {

    @Autowired
    ProductService productService;

    @GetMapping("/")
    @ApiOperation(value = "get all products",notes = "get all products",protocols = "Http-GET")
    public ResponseEntity<List<ProductResponseDto>> getAll() {
        List<ProductResponseDto> all = productService.findAll();
        return new ResponseEntity<>(all, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "get any product by product id",notes = "get any product by product id",protocols = "Http-GET")
    public ResponseEntity<ProductResponseDto> getById(@PathVariable int id) {
        ProductResponseDto get = productService.findById(id);
        return new ResponseEntity<>(get,HttpStatus.OK);
    }

    @GetMapping("/brand/{productBrand}")
    @ApiOperation(value = "get list of products by productbrand",notes = "get list of products by productbrand",protocols = "Http-GET")
    public ResponseEntity<List<ProductResponseDto>> getByProductBrand(@PathVariable String productBrand) {
        List<ProductResponseDto> all = productService.findByProductBrand(productBrand);
        return new ResponseEntity<>(all,HttpStatus.OK);
    }

    @GetMapping("/search/{productName}")
    @ApiOperation(value = "get list of products by productname",notes = "get list of products by productname",protocols = "Http-GET")
    public ResponseEntity<List<ProductResponseDto>> findByProductName(@PathVariable String productName) {
        List<ProductResponseDto> all = productService.findByProductName(productName);
        return new ResponseEntity<>(all,HttpStatus.OK);

    }

    //@PostMapping("/update")
    @RequestMapping(value = "/update",method = {RequestMethod.PUT,RequestMethod.GET})
    @ApiOperation(value = "update any product by productid",notes = "update any product by productid",protocols = "Http-PUT")
    public ResponseEntity<ProductResponseDto> updateByProductId(@RequestBody ProductUpdateRequestDto productUpdateRequestDto) {
        ProductResponseDto dto = productService.updateByProductId(productUpdateRequestDto);
        return new ResponseEntity<>(dto,HttpStatus.OK);
    }

    @PostMapping("/add")
    @ApiOperation(value = "add a new product",notes = "add a new product",protocols = "Http-POST")
    public ResponseEntity<String> add(@RequestBody ProductCreateRequestDto dto) {
        boolean add = productService.add(dto);
        if (add) {
            return new ResponseEntity<>("successfully added",HttpStatus.OK);
        }
        else  {
            return new ResponseEntity<>("an error occured",HttpStatus.NOT_FOUND);

        }

    }

}
