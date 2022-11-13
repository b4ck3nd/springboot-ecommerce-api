package com.kodlamiyoruz.ecomm.controller;


import com.kodlamiyoruz.ecomm.dto.address.AddressCreateRequestDto;
import com.kodlamiyoruz.ecomm.dto.address.AddressResponseDto;
import com.kodlamiyoruz.ecomm.dto.address.AddressUpdateRequestDto;
import com.kodlamiyoruz.ecomm.model.Address;
import com.kodlamiyoruz.ecomm.repository.AddressRepository;
import com.kodlamiyoruz.ecomm.service.address.AddressService;
import io.swagger.annotations.ApiOperation;
import org.aspectj.weaver.patterns.DeclareTypeErrorOrWarning;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/address")
@ApiOperation(value = "Address Controller")
public class AddressController {

    @Autowired
    AddressService addressService;
    @Autowired
    AddressRepository addressRepository;

    @GetMapping("/")
    @ApiOperation(value = "get all addresses",notes = "get all addresses",protocols = "Http-GET")
    public ResponseEntity<List<AddressResponseDto>> getAll() {
        List<AddressResponseDto> dtos=addressService.findAll();
        return new ResponseEntity<>(dtos, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "get address by addressid",notes = "get address by addressid",protocols = " Http-GET")
    public ResponseEntity<AddressResponseDto> findByAddressId(@PathVariable int id) {
        AddressResponseDto dto=addressService.findByAddressId(id);
        return new ResponseEntity<>(dto,HttpStatus.OK);
    }

    @GetMapping("/user/{id}")
    @ApiOperation(value = "get addresses by userid",notes = "get addresses by userid",protocols = "Http-GET")
    public ResponseEntity<List<AddressResponseDto>> findAddressesByUserId(@PathVariable int id) {
        List<AddressResponseDto> all=addressService.findAdressesByUserId(id);
        return new ResponseEntity<>(all,HttpStatus.OK);
    }

    @RequestMapping(value = "/delete/{id}",method = {RequestMethod.GET, RequestMethod.DELETE})
    @ApiOperation(value = "delete any address by addressid",notes = "delete any address by addressid",protocols = "Http-DELETE")
    public ResponseEntity<String> deleteById(@PathVariable int id) {
        addressService.deleteByIdd(id);
        return new ResponseEntity<>("deleted",HttpStatus.OK);
    }

    @RequestMapping(value = "/update",method = {RequestMethod.GET,RequestMethod.PUT})
    @ApiOperation(value = "update any address by addressid",notes = "update any address by addressid",protocols = "Http-PUT")
    public ResponseEntity<String> updateByAddressId(@RequestBody AddressUpdateRequestDto dto) {
        addressService.updateByAddressId(dto);
        return new ResponseEntity<>("updated",HttpStatus.OK);
    }
    @PostMapping("/add")
    @ApiOperation(value = "add a new address",notes = "add a new address",protocols = "Http-POST")
    public ResponseEntity<String> add(@RequestBody AddressCreateRequestDto dto) {
        addressService.add(dto);
        return new ResponseEntity<>("added",HttpStatus.OK);
    }


}
