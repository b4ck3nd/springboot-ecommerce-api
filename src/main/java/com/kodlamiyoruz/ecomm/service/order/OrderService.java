package com.kodlamiyoruz.ecomm.service.order;

import com.kodlamiyoruz.ecomm.dto.order.OrderCreateRequestDto;
import com.kodlamiyoruz.ecomm.dto.order.OrderResponseDto;

import java.util.List;

public interface OrderService {
    void createOrder(OrderCreateRequestDto dto);
    OrderResponseDto  findOrderByOrderId(int id);
    List<OrderResponseDto> findAll();
    void deleteById(int id);
    void add(OrderCreateRequestDto dto);
    OrderResponseDto findById(int id);
}
