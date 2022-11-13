package com.kodlamiyoruz.ecomm.controller;


import com.kodlamiyoruz.ecomm.dto.product.comment.ProductCommentCreateRequestDto;
import com.kodlamiyoruz.ecomm.dto.product.comment.ProductCommentResponseDto;
import com.kodlamiyoruz.ecomm.service.product.comment.ProductCommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("api/productcomment")
public class ProductCommentController {

    @Autowired
    ProductCommentService commentService;

    @GetMapping("/")
    public ResponseEntity<List<ProductCommentResponseDto>> getAll() {
        List<ProductCommentResponseDto> all = commentService.findAll();
        return new ResponseEntity<>(all, HttpStatus.OK);
    }

    @PostMapping( "/add")
    public ResponseEntity<String> addComnentToProduct(@RequestBody ProductCommentCreateRequestDto dto) {
         commentService.addCommentToProduct(dto);
        return new ResponseEntity<>("added a comment to product",HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductCommentResponseDto> findById(@PathVariable  int id) {
        ProductCommentResponseDto comment = commentService.findById(id);
        return new ResponseEntity<>(comment,HttpStatus.OK);
    }

    @RequestMapping(value = "/{id}/delete",method = {RequestMethod.GET,RequestMethod.DELETE})
    public ResponseEntity<String> deleteyId(@PathVariable int id) {
        commentService.deleteById(id);
        return new ResponseEntity<>("deleted",HttpStatus.OK);
    }






}
