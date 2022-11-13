package com.kodlamiyoruz.ecomm.converter;


import com.kodlamiyoruz.ecomm.dto.order.OrderCreateRequestDto;
import com.kodlamiyoruz.ecomm.dto.order.OrderResponseDto;
import com.kodlamiyoruz.ecomm.model.Order;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class OrderConverter {

  public OrderResponseDto orderToOrderResponseDto(Order order ) {
      OrderResponseDto dto=new OrderResponseDto();
      String adrs=order.getAddress().toString();
      dto.setAddress(order.getAddress().toString());
      dto.setProductName(order.getProductName());
      dto.setProductPrice(order.getProductPrice());
      dto.setProductBrand(order.getProductBrand());
      dto.setOrderId(order.getId());
      dto.setUserName(order.getUser().getUserName());
      dto.setTotalPrice(order.getProductPrice() * order.getQuantity());
      dto.setQuantity(order.getQuantity());
      return dto;
  }


}


