package com.kodlamiyoruz.ecomm.dto.order;


import com.kodlamiyoruz.ecomm.dto.address.AddressResponseDto;
import com.kodlamiyoruz.ecomm.model.Address;
import lombok.Data;

@Data
public class OrderResponseDto {
    private String productName;
    private String productBrand;
    private double productPrice;
    private int quantity;
    private double totalPrice;
    private String userName;
    private String address;
    private int orderId;

}
