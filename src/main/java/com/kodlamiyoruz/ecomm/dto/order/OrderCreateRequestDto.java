package com.kodlamiyoruz.ecomm.dto.order;

import lombok.Data;

@Data
public class OrderCreateRequestDto {
    private int productId;
    private int addressId;
    private int userId;
    private int quantity;
}
