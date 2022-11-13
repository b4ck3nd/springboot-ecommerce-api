package com.kodlamiyoruz.ecomm.service.product;


import com.kodlamiyoruz.ecomm.converter.ProductCommentConverter;
import com.kodlamiyoruz.ecomm.converter.ProductConverter;
import com.kodlamiyoruz.ecomm.dto.product.ProductCreateRequestDto;
import com.kodlamiyoruz.ecomm.dto.product.ProductResponseDto;
import com.kodlamiyoruz.ecomm.dto.product.ProductUpdateRequestDto;
import com.kodlamiyoruz.ecomm.dto.product.comment.ProductCommentResponseDto;
import com.kodlamiyoruz.ecomm.exception.CategoryException;
import com.kodlamiyoruz.ecomm.exception.NotFoundException;
import com.kodlamiyoruz.ecomm.exception.ProductException;
import com.kodlamiyoruz.ecomm.exception.SellerException;
import com.kodlamiyoruz.ecomm.model.Category;
import com.kodlamiyoruz.ecomm.model.Product;
import com.kodlamiyoruz.ecomm.model.ProductComment;
import com.kodlamiyoruz.ecomm.model.Seller;
import com.kodlamiyoruz.ecomm.repository.CategoryRepository;
import com.kodlamiyoruz.ecomm.repository.ProductCommentRepository;
import com.kodlamiyoruz.ecomm.repository.ProductRepository;
import com.kodlamiyoruz.ecomm.repository.SellerRepository;
import com.kodlamiyoruz.ecomm.service.product.comment.ProductCommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
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
    ProductCommentRepository commentRepository;

    @Autowired
    ProductCommentConverter commentConverter;

    @Autowired
    ProductCommentService commentService;


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

    @Override
    public List<ProductResponseDto> findByProductName(String productName) {
        List<Product> all = productRepository.findByProductNameContaining(productName);
        if (CollectionUtils.isEmpty(all)) {
            throw new NotFoundException(productName);
        }
        return productConverter.productListToProductResponseDtoList(all);
    }

    @Override
    public List<ProductResponseDto> findByProductBrand(String productBrand) {
        List<Product> all = productRepository.findByProductBrandContaining(productBrand);
        if (CollectionUtils.isEmpty(all)) {
            throw new NotFoundException(productBrand);
        }
        return productConverter.productListToProductResponseDtoList(all);
    }

    @Override
    public ProductResponseDto updateByProductId(ProductUpdateRequestDto productUpdateRequestDto) {
        /*
        Optional<Product> get = productRepository.findById(id);
        if (!get.isPresent()) {
            throw new NotFoundException(id);
        }
        Product product=updateProduct(get.get(), productUpdateRequestDto);
        return  productConverter.productToProductResponseDto(productRepository.save(product));

        */
        if ( !(productRepository.existsById(productUpdateRequestDto.getProductId())) ) {
            throw new ProductException(productUpdateRequestDto.getProductId());
        }
        Optional<Product> prod = productRepository.findById(productUpdateRequestDto.getProductId());
        Product product=updateProduct(prod.get(),productUpdateRequestDto);
        productRepository.save(product);
        return productConverter.productToProductResponseDto(product);
    }

    @Override
    public List<ProductResponseDto> findAll() {
        List<Product> all = productRepository.findAll();

        return productConverter.productListToProductResponseDtoList(all);
    }

    @Override
    public void deleteById(int id) {
        if (productRepository.existsById(id)) {
            productRepository.deleteById(id);
        }
        else {
            throw new ProductException(id);
        }

    }

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
