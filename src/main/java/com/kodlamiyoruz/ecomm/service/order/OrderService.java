package com.kodlamiyoruz.ecomm.service.order;

import com.kodlamiyoruz.ecomm.dto.order.OrderCreateRequestDto;
import com.kodlamiyoruz.ecomm.dto.order.OrderResponseDto;

import java.util.List;

public interface OrderService {
    void createOrder(OrderCreateRequestDto dto);
    List<OrderResponseDto> findAll();
    void deleteById(int id);
    OrderResponseDto findById(int id);
}
