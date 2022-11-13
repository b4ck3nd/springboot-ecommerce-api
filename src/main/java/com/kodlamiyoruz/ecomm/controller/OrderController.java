package com.kodlamiyoruz.ecomm.controller;


import com.kodlamiyoruz.ecomm.dto.order.OrderCreateRequestDto;
import com.kodlamiyoruz.ecomm.dto.order.OrderResponseDto;
import com.kodlamiyoruz.ecomm.service.order.OrderService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/order")
@Api(value = "Order Controller")
public class OrderController {

    @Autowired
    OrderService orderService;

    @GetMapping("/")
    public ResponseEntity<List<OrderResponseDto>> getAll() {
        List<OrderResponseDto> all = orderService.findAll();
        return new ResponseEntity<>(all, HttpStatus.OK);
    }


    @GetMapping("/{id}")
    public ResponseEntity<OrderResponseDto> findById(@PathVariable int id) {
        OrderResponseDto dto = orderService.findById(id);
        return new ResponseEntity<>(dto,HttpStatus.OK);

    }

    //@PostMapping("/add")
    @RequestMapping(value = "/add",method = {RequestMethod.GET,RequestMethod.POST})
    public ResponseEntity<String> createOrder(@RequestBody OrderCreateRequestDto dto) {
        orderService.createOrder(dto);
        return new ResponseEntity<>("added",HttpStatus.OK);
    }

    @RequestMapping(value = "/{id}/delete",method = {RequestMethod.GET,RequestMethod.DELETE})
    public ResponseEntity<String> deleteByID(@PathVariable int id) {
        orderService.deleteById(id);
        return new ResponseEntity<>("deleted",HttpStatus.OK);
    }



}
