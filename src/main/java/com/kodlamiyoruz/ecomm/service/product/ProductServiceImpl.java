package com.kodlamiyoruz.ecomm.service.product;


import com.kodlamiyoruz.ecomm.converter.ProductCommentConverter;
import com.kodlamiyoruz.ecomm.converter.ProductConverter;
import com.kodlamiyoruz.ecomm.dto.product.*;
import com.kodlamiyoruz.ecomm.dto.product.comment.ProductCommentResponseDto;
import com.kodlamiyoruz.ecomm.exception.*;
import com.kodlamiyoruz.ecomm.model.*;
import com.kodlamiyoruz.ecomm.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    ProductRepository productRepository;

    @Autowired
    ProductConverter productConverter;

    @Autowired
    CategoryRepository categoryRepository;

    @Autowired
    SellerRepository sellerRepository;

    @Autowired
    ProductCommentConverter commentConverter;




    @Transactional
    @CachePut(cacheNames = "product",key = "#dto.id")
    @Override
    public boolean add(ProductCreateRequestDto dto) {
        Product product = productConverter.productCreateDtoToProduct(dto);
        if (!(categoryRepository.existsById(dto.getCategoryId()))) {
            throw new CategoryException(dto.getCategoryId());
        }
        Category category = categoryRepository.findById(dto.getCategoryId()).get();
        Seller seller = sellerRepository.findById(dto.getSellerId()).get();

        if (!sellerRepository.existsById(dto.getSellerId())) {
            throw new SellerException("not found any seller with this id: " + dto.getSellerId());
        }


        product.setCategory(category);
        List<Product> productList=new ArrayList<>();
        productList.add(product);
        category.setProducts(productList);
        product.setSeller(seller);
        seller.setProducts(productList);
        productRepository.save(product);
        categoryRepository.save(category);
        sellerRepository.save(seller);


        return true;
    }

    @Cacheable(cacheNames = "product",key = "#id")
    @Override
    public ProductResponseDto findById(int id) {
        Optional<Product> byId = productRepository.findById(id);

        if (!byId.isPresent())  {
            throw new NotFoundException(id);
        }
        String categoryName=byId.get().getCategory().getCategoryName();
        String sellerName = byId.get().getSeller().getName();
        ProductResponseDto dto = productConverter.productToProductResponseDto(byId.get());
        return addCategoryNameAndSellerNameToProductResponseDto(dto,categoryName,sellerName);
    }

    @Cacheable(cacheNames = "product",key = "#productName")
    @Override
    public List<ProductResponseDto> findByProductName(String productName) {
        List<Product> all = productRepository.findByProductNameContaining(productName);
        if (CollectionUtils.isEmpty(all)) {
            throw new NotFoundException(productName);
        }
        return productConverter.productListToProductResponseDtoList(all);
    }

    @Cacheable(cacheNames = "product",key = "#productBrand")
    @Override
    public List<ProductResponseDto> findByProductBrand(String productBrand) {
        List<Product> all = productRepository.findByProductBrandContaining(productBrand);
        if (CollectionUtils.isEmpty(all)) {
            throw new NotFoundException(productBrand);
        }
        return productConverter.productListToProductResponseDtoList(all);
    }

    @Transactional
    @CachePut(cacheNames = "product",key = "#dto.id",condition = "#result != null")
    @Override
    public ProductResponseDto updateByProductId(ProductUpdateRequestDto dto) {
        /*
        Optional<Product> get = productRepository.findById(id);
        if (!get.isPresent()) {
            throw new NotFoundException(id);
        }
        Product product=updateProduct(get.get(), productUpdateRequestDto);
        return  productConverter.productToProductResponseDto(productRepository.save(product));

        */
        if ( !(productRepository.existsById(dto.getProductId())) ) {
            throw new ProductException(dto.getProductId());
        }
        Optional<Product> prod = productRepository.findById(dto.getProductId());
        Product product=updateProduct(prod.get(),dto);
        productRepository.save(product);
        return productConverter.productToProductResponseDto(product);
    }

    @Cacheable(cacheNames = "product")
    @Override
    public List<ProductResponseDto> findAll() {
        List<Product> all = productRepository.findAll();

        return productConverter.productListToProductResponseDtoList(all);
    }

    @Transactional
    @CacheEvict(cacheNames = "product",key = "#id")
    @Override
    public void deleteById(int id) {
        if (productRepository.existsById(id)) {
            productRepository.deleteById(id);
        }
        else {
            throw new ProductException(id);
        }

    }

    @Cacheable(cacheNames = "comment",key = "#id")
    @Override
    public List<ProductCommentResponseDto> findProductCommentsByProductId(int id) {

        if (!productRepository.existsById(id)) {
            throw new ProductException(id);
        }
        Product product = productRepository.findById(id).get();
        List<ProductComment> commentList = product.getProductComments();
        return commentConverter.productCommentListToProductCommentDtoList(commentList);

        /*
        List<ProductComment> comments = productRepository.findById(id).get().getProductComments();
        return  commentConverter.productCommentListToProductCommentDtoList(comments);
        */
    }
    /*

    @Override
    public List<ProductResponseDto> searchProductByProductName(String productName) {
        List<Product> products = productRepository.findByProductNameContaining(productName);
        return  productConverter.productListToProductResponseDtoList(products);
    }

    @Override
    public List<ProductResponseDto> searchProductByProductBrand(String productBrand) {
        List<Product> products = productRepository.findByProductBrandContaining(productBrand);
        return productConverter.productListToProductResponseDtoList(products);
    }

     */



    private Product updateProduct(Product product, ProductUpdateRequestDto dto) {
        Product p=product;
        p.setProductPrice(dto.getProductPrice());
        p.setStock(dto.getStock());
        p.setProductName(dto.getProductName());
        p.setProductBrand(dto.getProductBrand());
        return p;
    }

    private ProductResponseDto addCategoryNameAndSellerNameToProductResponseDto(ProductResponseDto responseDto,String categoryName,String sellerName) {
        ProductResponseDto dto=responseDto;
        dto.setCategoryName(categoryName);
        dto.setSellerName(sellerName);
        return dto;
    }


    /*

    @PostConstruct
    public void test1() {
        Product product=new Product();
        product.setProductPrice(100);
        product.setProductBrand("mavi");
        product.setStock(150);
        product.setProductName("mavi polar yaka tshirt");
        productRepository.save(product);
    }
    @PostConstruct
    public void test2() {
        Product product=new Product();
        product.setProductPrice(100);
        product.setProductBrand("kirmizi");
        product.setStock(150);
        product.setProductName("kirmizi polar yaka tshirt");
        productRepository.save(product);

    }
    @PostConstruct
    public void test3() {
        Product product=new Product();
        product.setProductPrice(100);
        product.setProductBrand("yesil");
        product.setStock(150);
        product.setProductName("yesil polar yaka tshirt");
        productRepository.save(product);

    }

     */
}
