package com.kodlamiyoruz.ecomm.service.seller;


import com.kodlamiyoruz.ecomm.converter.ProductConverter;
import com.kodlamiyoruz.ecomm.converter.SellerConverter;
import com.kodlamiyoruz.ecomm.dto.product.ProductResponseDto;
import com.kodlamiyoruz.ecomm.dto.seller.SellerCreateRequestDto;
import com.kodlamiyoruz.ecomm.dto.seller.SellerResponseDto;
import com.kodlamiyoruz.ecomm.dto.seller.SellerUpdateRequestDto;
import com.kodlamiyoruz.ecomm.exception.CategoryException;
import com.kodlamiyoruz.ecomm.exception.NotFoundException;
import com.kodlamiyoruz.ecomm.exception.SellerException;
import com.kodlamiyoruz.ecomm.model.Product;
import com.kodlamiyoruz.ecomm.model.Seller;
import com.kodlamiyoruz.ecomm.repository.ProductRepository;
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

    @Autowired
    ProductConverter productConverter;

    @Autowired
    ProductRepository productRepository;


    @Override
    public void add(SellerCreateRequestDto sellerCreateRequestDto){
        if (sellerRepository.existsSellerByEmail(sellerCreateRequestDto.getEmail())) {
            throw new SellerException("email not found");
        }
        sellerRepository.save(sellerConverter.sellerCreateRequestDtoToSeller(sellerCreateRequestDto));
    }

    @Override
    public boolean deleteById(int id) {
        /*
        if (sellerRepository.existsById(id)) {

            Optional<Seller> seller = sellerRepository.findById(id);
            if (!CollectionUtils.isEmpty(seller.get().getProducts())) {
                List<Product> products = seller.get().getProducts();
                products.forEach(val -> {
                    productRepository.delete(val);
                    products.remove(val);
                });
            }
            sellerRepository.deleteById(id);
            return true;
        }
        */
        if (sellerRepository.existsById(id)) {
            sellerRepository.deleteById(id);
            return true;
        } else return false;
    }

    @Override
    public List<SellerResponseDto> findAll() {
        List<Seller> sellers=sellerRepository.findAll();

        return sellerConverter.sellerListToSellerResponseDtoList(sellers);

    }
    @Override
    public SellerResponseDto findById(int id) {
        Optional<Seller> byId = sellerRepository.findById(id);
        if (!byId.isPresent()) {
            throw new SellerException(id);
        }
        Seller seller=byId.get();

        SellerResponseDto dto=sellerConverter.sellerToSellerResponseDto(seller);
        return dto;
    }

    @Override
    public SellerResponseDto updateById(SellerUpdateRequestDto dto) {
        if (!sellerRepository.existsById(dto.getSellerId())) {
            throw new SellerException(dto.getSellerId());
        }
        Optional<Seller> seller = sellerRepository.findById(dto.getSellerId());
        seller.get().setName(dto.getName());
        seller.get().setEmail(dto.getEmail());
        sellerRepository.save(seller.get());
        return sellerConverter.sellerToSellerResponseDto(seller.get());

    }

    @Override
    public List<ProductResponseDto> findAllProductsBySellerId(int id) {
        if (!sellerRepository.existsById(id)) {
            throw new SellerException(id);
        }
        Seller seller = sellerRepository.findById(id).get();
        List<Product> products = seller.getProducts();
        return productConverter.productListToProductResponseDtoList(products);
    }


    /*
    @PostConstruct
    private void test() {
        Seller seller=new Seller();
        seller.setName("seller 1");
        seller.setEmail("seller1@seller.com");
        sellerRepository.save(seller);
    }

    @PostConstruct
    private void test2() {
        Seller seller=new Seller();
        seller.setEmail("seller2@seller.co");
        seller.setName("iki numarali seller");
        sellerRepository.save(seller);
    }

     */
}
