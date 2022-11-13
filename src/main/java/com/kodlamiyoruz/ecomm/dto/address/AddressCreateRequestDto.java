package com.kodlamiyoruz.ecomm.dto.address;


import lombok.Data;

@Data
public class AddressCreateRequestDto {
    private String city;
    private String country;
    private String street;
    private String district;
    private String apartmentNumber;
    private int userId;

}
