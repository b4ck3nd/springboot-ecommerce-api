package com.kodlamiyoruz.ecomm.service.seller;


import com.kodlamiyoruz.ecomm.converter.SellerConverter;
import com.kodlamiyoruz.ecomm.dto.seller.SellerCreateRequestDto;
import com.kodlamiyoruz.ecomm.dto.seller.SellerResponseDto;
import com.kodlamiyoruz.ecomm.exception.CategoryException;
import com.kodlamiyoruz.ecomm.exception.NotFoundException;
import com.kodlamiyoruz.ecomm.exception.SellerException;
import com.kodlamiyoruz.ecomm.model.Seller;
import com.kodlamiyoruz.ecomm.repository.SellerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.Optional;

@Service
public class SellerServiceImpl implements SellerService {

    @Autowired
    SellerRepository sellerRepository;

    @Autowired
    SellerConverter sellerConverter;


    @Override
    public void add(SellerCreateRequestDto sellerCreateRequestDto) {
        if (sellerRepository.existsSellerByEmail(sellerCreateRequestDto.getEmail())) {
            throw new SellerException("email was found");
        }
        sellerRepository.save(sellerConverter.sellerCreateRequestDtoToSeller(sellerCreateRequestDto));
    }



    @Override
    public boolean deleteById(int id) {

        if (sellerRepository.existsById(id)) {
            sellerRepository.deleteById(id);
            return true;
        }
        return false;


    }



    @Override
    public List<SellerResponseDto> findAll() {
        List<Seller> sellers=sellerRepository.findAll();

        return sellerConverter.sellerListToSellerResponseDtoList(sellers);

    }


    @Override
    public SellerResponseDto findById(int id) {
        Seller seller=sellerRepository.findBySellerId(id);
        SellerResponseDto dto=sellerConverter.sellerToSellerResponseDto(seller);
        return dto;

    }

    @PostConstruct
    private void test() {
        Seller seller=new Seller();
        seller.setName("seller 1");
        seller.setEmail("seller1@seller");
        sellerRepository.save(seller);
    }

    @PostConstruct
    private void test2() {
        Seller seller=new Seller();
        seller.setEmail("seller2@seller");
        seller.setName("iki numarali seller");
        sellerRepository.save(seller);
    }
}
