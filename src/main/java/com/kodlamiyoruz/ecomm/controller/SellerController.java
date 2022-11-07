package com.kodlamiyoruz.ecomm.controller;


import com.kodlamiyoruz.ecomm.dto.seller.SellerCreateRequestDto;
import com.kodlamiyoruz.ecomm.dto.seller.SellerResponseDto;
import com.kodlamiyoruz.ecomm.service.seller.SellerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/seller")
public class SellerController {

    @Autowired
    SellerService sellerService;

    @GetMapping("/")
    public ResponseEntity<List<SellerResponseDto>> getAll() {
        return new ResponseEntity<>(sellerService.findAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<SellerResponseDto> findById(@PathVariable int id) {
        SellerResponseDto sellerResponseDto=sellerService.findById(id);
        return new ResponseEntity<>(sellerResponseDto,HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<Void> add(@RequestBody SellerCreateRequestDto sellerCreateRequestDto) {
        sellerService.add(sellerCreateRequestDto);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    //@DeleteMapping("/delete/{id}")
    @RequestMapping(value = "/delete/{id}",method = { RequestMethod.DELETE,RequestMethod.GET })
    public ResponseEntity<String> deleteById(@PathVariable int id) {
        boolean status = sellerService.deleteById(id);
        if (status) {
            return new ResponseEntity<>("deleted",HttpStatus.OK);
        }
        else {
            return new ResponseEntity<>("not found any seller with this id: " + id , HttpStatus.OK);
        }

    }
}
