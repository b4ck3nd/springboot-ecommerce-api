package com.kodlamiyoruz.ecomm.service.address;

import com.kodlamiyoruz.ecomm.dto.address.AddressCreateRequestDto;
import com.kodlamiyoruz.ecomm.dto.address.AddressResponseDto;
import com.kodlamiyoruz.ecomm.dto.address.AddressUpdateRequestDto;


import java.util.List;

public interface AddressService {
    void add(AddressCreateRequestDto dto);
    List<AddressResponseDto> findAll();
    AddressResponseDto findByAddressId(int addressId);
    List<AddressResponseDto> findAdressesByUserId(int userId);
    void deleteByIdd(int addressId);
    void updateByAddressId(AddressUpdateRequestDto dto);



}
