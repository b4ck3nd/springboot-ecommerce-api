package com.kodlamiyoruz.ecomm.service.category;


import com.kodlamiyoruz.ecomm.converter.CategoryConverter;
import com.kodlamiyoruz.ecomm.converter.ProductConverter;
import com.kodlamiyoruz.ecomm.dto.category.CategoryCreateRequestDto;
import com.kodlamiyoruz.ecomm.dto.category.CategoryResponseDto;
import com.kodlamiyoruz.ecomm.dto.product.ProductResponseDto;
import com.kodlamiyoruz.ecomm.exception.CategoryException;
import com.kodlamiyoruz.ecomm.exception.NotFoundException;
import com.kodlamiyoruz.ecomm.model.Category;
import com.kodlamiyoruz.ecomm.model.Product;
import com.kodlamiyoruz.ecomm.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.Optional;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    CategoryRepository categoryRepository;

    @Autowired
    CategoryConverter categoryConverter;

    @Autowired
    ProductConverter productConverter;



    @Override
    public CategoryResponseDto findByCategoryName(String categoryName) {
        return null;
    }

    @Override
    public List<CategoryResponseDto> findAll() {
        List<Category> all = categoryRepository.findAll();
        if (CollectionUtils.isEmpty(all)) {
            throw new NotFoundException();
        }

        return categoryConverter.categoryListToCategoryResponseDtoList(all);
    }

    @Override
    public CategoryResponseDto findById(int id) {
        if (categoryRepository.existsById(id)) {
            Optional<Category> get = categoryRepository.findById(id);
            Category cat=get.get();
            CategoryResponseDto dto=categoryConverter.categoryToCategoryResponseDto(cat);
            dto.setTotalProducts(cat.getProducts().size());
            return dto;
        }
        else {
            throw new NotFoundException(id);
        }
    }

    @Transactional
    @Override
    public void add(CategoryCreateRequestDto categoryCreateRequestDto) {
        Category category = categoryConverter.categoryCreateDtoToCategory(categoryCreateRequestDto);
        categoryRepository.save(category);
    }
    @Transactional
    @Override
    public String deleteById(int id) {
       if (categoryRepository.existsById(id)) {
           Optional<Category> get = categoryRepository.findById(id);
           String categoryName=get.get().getCategoryName();
           categoryRepository.deleteById(id);
           return  categoryName + " category deleted";
       }
       else {
           return "category not found with this id: " + id;
       }
    }

    @Override
    public List<ProductResponseDto> findAllProductByCategoryId(int id) {
        if (!categoryRepository.existsById(id)) {
            throw new CategoryException(id);
        }
        List<Product> products = categoryRepository.findById(id).get().getProducts();
        return  productConverter.productListToProductResponseDtoList(products);

    }

    @PostConstruct
    public void test() {
        Category c=new Category();
        c.setCategoryName("test");
        categoryRepository.save(c);
    }


}
