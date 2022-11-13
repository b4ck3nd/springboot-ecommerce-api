package com.kodlamiyoruz.ecomm.converter;


import com.kodlamiyoruz.ecomm.dto.address.AddressCreateRequestDto;
import com.kodlamiyoruz.ecomm.dto.address.AddressResponseDto;
import com.kodlamiyoruz.ecomm.model.Address;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class AddressConverter {
    public Address addressCreateRequestDtoToAddress(AddressCreateRequestDto dto) {
        Address address=new Address();
        address.setCity(dto.getCity());
        address.setCountry(dto.getCountry());
        address.setDistrict(dto.getDistrict());
        address.setStreet(dto.getStreet());
        address.setApartmentNumber(dto.getApartmentNumber());
        return address;
    }
    public List<AddressResponseDto> addressListToAddressResponseDtoList(List<Address> addresses) {
        List<AddressResponseDto> dtos=new ArrayList<>();
        for (Address address : addresses) {
            AddressResponseDto dto=new AddressResponseDto();
            dto.setCity(address.getCity());
            dto.setStreet(address.getStreet());
            dto.setDistrict(address.getDistrict());
            dto.setCountry(address.getCountry());
            dto.setApartmentNumber(address.getApartmentNumber());
            dto.setUserName(address.getUser().getUserName());
            dto.setAddressId(address.getId());
            dtos.add(dto);
        }
        return dtos;
    }
    public AddressResponseDto addressToAddressResponseDto(Address address) {
        AddressResponseDto dto=new AddressResponseDto();
        dto.setCity(address.getCity());
        dto.setStreet(address.getStreet());
        dto.setDistrict(address.getDistrict());
        dto.setCountry(address.getCountry());
        dto.setApartmentNumber(address.getApartmentNumber());
        dto.setAddressId(address.getId());
        dto.setUserName(address.getUser().getUserName());
        return dto;
    }

}
