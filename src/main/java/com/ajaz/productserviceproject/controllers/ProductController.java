package com.ajaz.productserviceproject.controllers;

import com.ajaz.productserviceproject.dtos.ExceptionDto;
import com.ajaz.productserviceproject.dtos.GenericProductDto;
import com.ajaz.productserviceproject.exceptions.NotFoundException;
import com.ajaz.productserviceproject.models.Product;
import com.ajaz.productserviceproject.services.ProductService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.actuate.autoconfigure.observation.ObservationProperties;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {

    private ProductService productService;

    public ProductController(@Qualifier("fakeStoreProductService") ProductService productService){
        this.productService = productService;
    }
    @GetMapping("/{id}")
    public GenericProductDto getProductById(@PathVariable("id") Long id) throws NotFoundException {

        return productService.getProductById(id);
    }

//    @ExceptionHandler(NotFoundException.class)
//    private ResponseEntity<ExceptionDto> handleNotFoundException(NotFoundException notFoundException){
//        return new ResponseEntity<>(
//                new ExceptionDto(notFoundException.getMessage(), HttpStatus.NOT_FOUND),
//                HttpStatus.NOT_FOUND
//        );
//    }

    @GetMapping()
    public List<GenericProductDto> getAllProducts(){
        return productService.getAllProducts();
    }

    @PostMapping()
    public GenericProductDto createProduct(@RequestBody GenericProductDto genericProductDto){

        return productService.createProduct(genericProductDto);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<GenericProductDto> deleteProductById(@PathVariable("id") Long id) throws NotFoundException{
        return new ResponseEntity<>(productService.deleteProductById(id), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public GenericProductDto updateProductById(@PathVariable("id") Long id, @RequestBody GenericProductDto genericProductDto) throws NotFoundException{

        return productService.updateProductById(id, genericProductDto);
    }


}
