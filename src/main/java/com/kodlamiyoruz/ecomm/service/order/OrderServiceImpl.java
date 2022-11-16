package com.kodlamiyoruz.ecomm.service.order;


import com.kodlamiyoruz.ecomm.converter.OrderConverter;
import com.kodlamiyoruz.ecomm.dto.order.OrderCreateRequestDto;
import com.kodlamiyoruz.ecomm.dto.order.OrderResponseDto;
import com.kodlamiyoruz.ecomm.exception.*;
import com.kodlamiyoruz.ecomm.model.*;
import com.kodlamiyoruz.ecomm.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    OrderRepository orderRepository;

    @Autowired
    ProductRepository productRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    AddressRepository addressRepository;

    @Autowired
    OrderConverter orderConverter;

    @Autowired
    CreditCardRepository creditCardRepository;


    @Transactional
    @Override
    public void createOrder(OrderCreateRequestDto dto) {
        if ( !productRepository.existsById(dto.getProductId())) {
            throw new ProductException(dto.getProductId());
        }
        if (!userRepository.existsById(dto.getUserId())) {
            throw new CustomUserException(dto.getUserId());
        }
        if (!addressRepository.existsById(dto.getAddressId())) {
            throw new AddressException(dto.getAddressId());
        }
        if (!creditCardRepository.existsById(dto.getCreditCardId())) {
            throw new NotFoundException("not found any creditcard with this id: " + dto.getCreditCardId());
        }

        Product product = productRepository.findById(dto.getProductId()).get();
        User user = userRepository.findById(dto.getUserId()).get();
        Address address = addressRepository.findById(dto.getAddressId()).get();
        CreditCard creditCard = creditCardRepository.findById(dto.getCreditCardId()).get();
        if (dto.getQuantity()> product.getStock()) {
            throw new ProductException("not enough stock for this product: " + product.getProductName());
        }
        int currentStock=product.getStock();
        product.setStock(currentStock-dto.getQuantity());
        Order order=new Order();
        order.setUser(user);
        order.setAddress(address);
        order.setQuantity(dto.getQuantity());
        order.setProductName(product.getProductName());
        order.setProductPrice(product.getProductPrice());
        order.setProductBrand(product.getProductBrand());
        order.setCreditCard(creditCard);
        List<Order> orderList=new ArrayList<>();
        orderList.add(order);
        user.setOrders(orderList);
        userRepository.save(user);
        productRepository.save(product);
        orderRepository.save(order);


    }


    @Override
    public List<OrderResponseDto> findAll() {
        List<Order> all = orderRepository.findAll();
        List<OrderResponseDto> dtos=new ArrayList<>();
        for (Order order :  all) {
            OrderResponseDto dto = orderConverter.orderToOrderResponseDto(order);
            dto.setQuantity(order.getQuantity());
            String adrs=order.getAddress().toString();
            dto.setAddress(adrs);
            dto.setUserName(order.getUser().getUserName());
            dtos.add(dto);
        }
        return dtos;
    }

    @Transactional
    @Override
    public void deleteById(int id) {
        if (!orderRepository.existsById(id)) {
            throw new OrderException(id);
        }
        orderRepository.deleteById(id);
    }

   

    @Override
    public OrderResponseDto findById(int id) {
        if (!orderRepository.existsById(id)) {
            throw new OrderException(id);
        }
        Order order = orderRepository.findById(id).get();
        OrderResponseDto dto = orderConverter.orderToOrderResponseDto(order);
       return dto;

    }

}
