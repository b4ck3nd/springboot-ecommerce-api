package com.kodlamiyoruz.ecomm.service.category;


import com.kodlamiyoruz.ecomm.converter.CategoryConverter;
import com.kodlamiyoruz.ecomm.dto.category.CategoryCreateRequestDto;
import com.kodlamiyoruz.ecomm.dto.category.CategoryResponseDto;
import com.kodlamiyoruz.ecomm.exception.NotFoundException;
import com.kodlamiyoruz.ecomm.model.Category;
import com.kodlamiyoruz.ecomm.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
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

    @Override
    public void add(CategoryCreateRequestDto categoryCreateRequestDto) {
        Category category = categoryConverter.categoryCreateDtoToCategory(categoryCreateRequestDto);
        categoryRepository.save(category);
    }

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

    /*

    @PostConstruct
    public void test1() {
        Category category=new Category();
        category.setCategoryName("gaming laptop");
        categoryRepository.save(category);
    }
    @PostConstruct
    public void test2() {
        Category category=new Category();
        category.setCategoryName("laptop");
        categoryRepository.save(category);
    }
    @PostConstruct
    public void test3() {
        Category category=new Category();
        category.setCategoryName("gaming pad");
        categoryRepository.save(category);
    }
    @PostConstruct
    public void test4() {
        Category category=new Category();
        category.setCategoryName("gaming headphones");
        categoryRepository.save(category);
    }
    @PostConstruct
    public void test5() {
        Category category=new Category();
        category.setCategoryName("kaban");
        categoryRepository.save(category);
    }



     */


}
