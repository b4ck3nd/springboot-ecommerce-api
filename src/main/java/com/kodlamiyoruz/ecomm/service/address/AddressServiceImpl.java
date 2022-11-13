package com.kodlamiyoruz.ecomm.service.address;


import com.kodlamiyoruz.ecomm.converter.AddressConverter;
import com.kodlamiyoruz.ecomm.dto.address.AddressCreateRequestDto;
import com.kodlamiyoruz.ecomm.dto.address.AddressResponseDto;
import com.kodlamiyoruz.ecomm.dto.address.AddressUpdateRequestDto;
import com.kodlamiyoruz.ecomm.exception.AddressException;
import com.kodlamiyoruz.ecomm.exception.CustomUserException;
import com.kodlamiyoruz.ecomm.model.Address;
import com.kodlamiyoruz.ecomm.model.User;
import com.kodlamiyoruz.ecomm.repository.AddressRepository;
import com.kodlamiyoruz.ecomm.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;
import java.util.Optional;

@Service
public class AddressServiceImpl implements  AddressService {

    @Autowired
    AddressRepository addressRepository;
    @Autowired
    UserRepository userRepository;

    @Autowired
    AddressConverter addressConverter;


    @Override
    public void add(AddressCreateRequestDto dto) {
        if( !userRepository.existsById(dto.getUserId())) {
            throw new CustomUserException(dto.getUserId());
        }
        Optional<User> user = userRepository.findById(dto.getUserId());
        Address address=addressConverter.addressCreateRequestDtoToAddress(dto);
        address.setUser(user.get());
        addressRepository.save(address);
    }

    @Override
    public List<AddressResponseDto> findAll() {
        List<Address> all = addressRepository.findAll();
        return addressConverter.addressListToAddressResponseDtoList(all);
    }


    @Override
    public AddressResponseDto findByAddressId(int addressId) {
        Optional<Address> address = addressRepository.findById(addressId);
        if (!address.isPresent()) {
            throw new AddressException(addressId);
        }
        return addressConverter.addressToAddressResponseDto(address.get());
    }

    @Override
    public List<AddressResponseDto> findAdressesByUserId(int userId) {
        if (!userRepository.existsById(userId)) {
            throw new CustomUserException(userId);
        }
        Optional<User> user = userRepository.findById(userId);
        List<Address> address = user.get().getAddress();
        List<AddressResponseDto> dtos = addressConverter.addressListToAddressResponseDtoList(address);
        return dtos;
    }

    @Override
    public void deleteByIdd(int addressId) {
        if ( addressRepository.existsById(addressId)) {
            addressRepository.deleteById(addressId);
        }
        else {
            throw new AddressException(addressId);
        }


    }

    @Override
    public void updateByAddressId(AddressUpdateRequestDto dto) {
        Optional<Address> address = addressRepository.findById(dto.getAddressId());
        if (address.isPresent()) {
            address.get().setCity(dto.getCity());
            address.get().setStreet(dto.getStreet());
            address.get().setCountry(dto.getCountry());
            address.get().setApartmentNumber(dto.getApartmentNumber());
            address.get().setDistrict(dto.getDistrict());
            addressRepository.save(address.get());
        }
        else {
            throw new AddressException(dto.getAddressId());
        }
    }


}
