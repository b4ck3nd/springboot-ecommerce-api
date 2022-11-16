package com.kodlamiyoruz.ecomm.converter;


import com.kodlamiyoruz.ecomm.dto.order.OrderCreateRequestDto;
import com.kodlamiyoruz.ecomm.dto.order.OrderResponseDto;
import com.kodlamiyoruz.ecomm.model.Order;
import org.springframework.stereotype.Component;

import javax.persistence.metamodel.ListAttribute;
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

  public List<OrderResponseDto> orderListToOrderResponseDtoList(List<Order> orders) {
      List<OrderResponseDto> dtos=new ArrayList<>();
      orders.forEach(value -> {
          OrderResponseDto dto=new OrderResponseDto();
          dto.setQuantity(value.getQuantity());
          dto.setOrderId(value.getId());
          dto.setProductBrand(value.getProductBrand());
          dto.setAddress(value.getAddress().toString());
          dto.setTotalPrice(value.getProductPrice() * value.getQuantity());
          dto.setProductName(value.getProductName());
          dto.setUserName(value.getProductName());
          dtos.add(dto);
      });
      return dtos;


  }


}


