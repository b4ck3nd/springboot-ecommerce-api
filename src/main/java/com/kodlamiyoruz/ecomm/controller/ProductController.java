package com.kodlamiyoruz.ecomm.controller;


import com.kodlamiyoruz.ecomm.dto.product.ProductCreateRequestDto;
import com.kodlamiyoruz.ecomm.dto.product.ProductResponseDto;
import com.kodlamiyoruz.ecomm.dto.product.ProductUpdateRequestDto;
import com.kodlamiyoruz.ecomm.service.product.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/product")
public class ProductController {

    @Autowired
    ProductService productService;

    @GetMapping("/")
    public ResponseEntity<List<ProductResponseDto>> getAll() {
        List<ProductResponseDto> all = productService.findAll();
        return new ResponseEntity<>(all, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductResponseDto> getById(@PathVariable int id) {
        ProductResponseDto get = productService.findById(id);
        return new ResponseEntity<>(get,HttpStatus.OK);
    }

    @GetMapping("/brand/{productBrand}")
    public ResponseEntity<List<ProductResponseDto>> getByProductBrand(@PathVariable String productBrand) {
        List<ProductResponseDto> all = productService.findByProductBrand(productBrand);
        return new ResponseEntity<>(all,HttpStatus.OK);
    }

    @GetMapping("/search/{productName}")
    public ResponseEntity<List<ProductResponseDto>> findByProductName(@PathVariable String productName) {
        List<ProductResponseDto> all = productService.findByProductName(productName);
        return new ResponseEntity<>(all,HttpStatus.OK);

    }

    @PostMapping("/update/{id}")
    public ResponseEntity<ProductResponseDto> updateByProductId(@PathVariable int id, @RequestBody ProductUpdateRequestDto productUpdateRequestDto) {
        ProductResponseDto dto = productService.updateByProductId(id, productUpdateRequestDto);
        return new ResponseEntity<>(dto,HttpStatus.OK);
    }

    @PostMapping("/add")
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
