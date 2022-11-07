package com.kodlamiyoruz.ecomm.controller;


import com.kodlamiyoruz.ecomm.dto.category.CategoryCreateRequestDto;
import com.kodlamiyoruz.ecomm.dto.category.CategoryResponseDto;
import com.kodlamiyoruz.ecomm.service.category.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/category")
public class CategoryController {

    @Autowired
    CategoryService categoryService;

    @GetMapping("/")
    public ResponseEntity<List<CategoryResponseDto>> getAll() {
        List<CategoryResponseDto> all = categoryService.findAll();
        return new ResponseEntity<>(all, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CategoryResponseDto> getById(@PathVariable int id) {
        CategoryResponseDto get = categoryService.findById(id);
        return new ResponseEntity<>(get,HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<Void>  add(@RequestBody CategoryCreateRequestDto dto) {
        categoryService.add(dto);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    //@DeleteMapping("/delete/{id}")
    @RequestMapping(value = "/delete/{id}",method = {RequestMethod.DELETE,RequestMethod.GET})
    public ResponseEntity<String> deleteById(@PathVariable int id) {
        String msg= categoryService.deleteById(id);
        return new ResponseEntity<>(msg,HttpStatus.OK);
    }
}
